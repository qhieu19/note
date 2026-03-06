const API_BASE_URL = 'http://localhost:8080/api';

// --- Initialization ---
document.addEventListener('DOMContentLoaded', () => {
    fetchDashboardData();
    // Re-bind hover/click effects after potential dynamic loads
});

// --- API Fetching ---
async function fetchDashboardData() {
    try {
        const [tasks, stats] = await Promise.all([
            fetch(`${API_BASE_URL}/tasks`).then(res => res.json()),
            fetch(`${API_BASE_URL}/dashboard/stats`).then(res => res.json())
        ]);

        renderStats(stats);
        renderTasks(tasks);
    } catch (err) {
        console.error('Failed to fetch dashboard data:', err);
    }
}

// --- Rendering ---
function renderStats(stats) {
    document.getElementById('stat-total').textContent = stats.totalTasks;
    document.getElementById('stat-done').textContent = stats.completedTasks;
    document.getElementById('stat-active').textContent = stats.activeTasks;
    document.getElementById('stat-urgent').textContent = stats.urgentTasks;
}

function renderTasks(tasks) {
    const lists = {
        'TODO': document.getElementById('list-todo'),
        'IN_PROGRESS': document.getElementById('list-inprogress'),
        'DONE': document.getElementById('list-done')
    };

    const counts = {
        'TODO': document.getElementById('count-todo'),
        'IN_PROGRESS': document.getElementById('count-inprogress'),
        'DONE': document.getElementById('count-done')
    };

    // Clear current lists
    Object.values(lists).forEach(list => list.innerHTML = '');

    // Reset counts
    const statusCounts = { 'TODO': 0, 'IN_PROGRESS': 0, 'DONE': 0 };

    tasks.forEach(task => {
        const list = lists[task.status];
        if (list) {
            list.appendChild(createTaskCard(task));
            statusCounts[task.status]++;
        }
    });

    // Update count labels
    Object.keys(counts).forEach(status => {
        counts[status].textContent = statusCounts[status];
    });
}

function createTaskCard(task) {
    const card = document.createElement('div');
    card.className = `task-card ${task.priority} ${task.status === 'DONE' ? 'done-card' : ''}`;

    // Color mapping for priority icons/status
    const colorMap = { 'high': 'var(--red)', 'med': 'var(--amber)', 'low': 'var(--green)' };
    const color = colorMap[task.priority] || 'var(--blue)';

    card.innerHTML = `
        <div class="card-actions">
            ${task.status === 'DONE' ? '<div class="ca-btn" title="Reopen">↺</div>' : '<div class="ca-btn" title="Edit">✎</div><div class="ca-btn" title="Move">→</div>'}
        </div>
        <div class="card-tags">
            ${task.tags.map(tag => `<span class="tag ${tag}">${tag}</span>`).join('')}
        </div>
        <div class="card-title">${task.title}</div>
        <div class="card-prog">
            <div class="card-prog-fill" style="width:${task.progress}%; background:${color}"></div>
        </div>
        <div class="card-foot">
            <div class="card-date ${task.priority === 'high' && task.status !== 'DONE' ? 'late' : ''}" style="${task.status === 'DONE' ? 'color:var(--green)' : ''}">
                ${task.status === 'DONE' ? '✓ ' : '⏱ '}${task.dueDate} ${task.priority === 'high' && task.status !== 'DONE' ? '⚠' : ''}
            </div>
            <div class="card-sub-count">${task.status === 'DONE' ? '☑' : '☐'} ${task.progress === 100 ? task.subtaskCount : 0}/${task.subtaskCount}</div>
        </div>
    `;

    // Add hover effect
    card.addEventListener('mouseenter', () => card.style.zIndex = 10);
    card.addEventListener('mouseleave', () => card.style.zIndex = '');

    return card;
}

// --- UI Interactions (Keep existing) ---
document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', () => {
        document.querySelectorAll('.nav-item').forEach(i => i.classList.remove('active'));
        item.classList.add('active');
    });
});

document.querySelectorAll('.vt-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        btn.closest('.view-toggle').querySelectorAll('.vt-btn').forEach(b => b.classList.remove('on'));
        btn.classList.add('on');
    });
});

const tabs = document.querySelectorAll('.panel-tab');
const tabContents = { 0: 'tab-activity', 1: 'tab-deadlines', 2: 'tab-stats' };
tabs.forEach((tab, i) => {
    tab.addEventListener('click', () => {
        tabs.forEach(t => t.classList.remove('on'));
        tab.classList.add('on');
        Object.values(tabContents).forEach(id => document.getElementById(id).style.display = 'none');
        document.getElementById(tabContents[i]).style.display = 'block';
    });
});

document.querySelectorAll('.col-chip').forEach(chip => {
    chip.addEventListener('click', () => {
        document.querySelectorAll('.col-chip').forEach(c => c.classList.remove('on'));
        chip.classList.add('on');
    });
});
