const API_BASE_URL = 'https://note-ol3j.onrender.com/api';

// --- Initialization ---
document.addEventListener('DOMContentLoaded', () => {
    fetchDashboardData();
});

// --- API Fetching ---
async function fetchDashboardData() {
    try {
        const [tasks, stats, activities, deadlines] = await Promise.all([
            fetch(`${API_BASE_URL}/tasks`).then(res => res.json()),
            fetch(`${API_BASE_URL}/dashboard/stats`).then(res => res.json()),
            fetch(`${API_BASE_URL}/dashboard/activities`).then(res => res.json()),
            fetch(`${API_BASE_URL}/dashboard/deadlines`).then(res => res.json())
        ]);

        renderStats(stats);
        renderTasks(tasks);
        renderActivities(activities);
        renderDeadlines(deadlines);
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
    Object.values(lists).forEach(list => { if (list) list.innerHTML = ''; });

    const statusCounts = { 'TODO': 0, 'IN_PROGRESS': 0, 'DONE': 0 };

    tasks.forEach(task => {
        const list = lists[task.status];
        if (list) {
            list.appendChild(createTaskCard(task));
            statusCounts[task.status]++;
        }
    });

    Object.keys(counts).forEach(status => {
        if (counts[status]) counts[status].textContent = statusCounts[status];
    });
}

function createTaskCard(task) {
    const card = document.createElement('div');
    card.className = `task-card ${task.priority} ${task.status === 'DONE' ? 'done-card' : ''}`;

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

    card.addEventListener('mouseenter', () => card.style.zIndex = 10);
    card.addEventListener('mouseleave', () => card.style.zIndex = '');

    return card;
}

function renderActivities(activities) {
    const feed = document.getElementById('activity-feed');
    if (!feed) return;
    feed.innerHTML = activities.map(act => `
        <div class="activity-item">
            <div class="act-dot" style="background:${act.color}"></div>
            <div class="act-content">
                <div class="act-text"><strong>${act.user}</strong> ${act.action}</div>
                <div class="act-time">${act.timeLabel}</div>
            </div>
        </div>
    `).join('');
}

function renderDeadlines(deadlines) {
    const list = document.getElementById('deadline-list');
    if (!list) return;
    list.innerHTML = deadlines.map(dl => `
        <div class="deadline-item">
            <div class="dl-date-box ${dl.urgent ? 'urgent' : ''}">
                <div class="dl-day">${dl.day}</div>
                <div class="dl-mon">${dl.month}</div>
            </div>
            <div class="dl-info">
                <div class="dl-name">${dl.title}</div>
                <div class="dl-meta">${dl.category} · ${dl.timeLeft}</div>
            </div>
            <div class="dl-pri ${dl.priority}" style="background:var(--${dl.priority}-bg);color:var(--${dl.priority})">${dl.priority}</div>
        </div>
    `).join('');
}

// --- UI Interactions ---
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
        Object.values(tabContents).forEach(id => {
            const el = document.getElementById(id);
            if (el) el.style.display = 'none';
        });
        const target = document.getElementById(tabContents[i]);
        if (target) target.style.display = 'block';
    });
});

document.querySelectorAll('.col-chip').forEach(chip => {
    chip.addEventListener('click', () => {
        document.querySelectorAll('.col-chip').forEach(c => c.classList.remove('on'));
        chip.classList.add('on');
    });
});
