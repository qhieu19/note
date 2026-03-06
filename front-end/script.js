// Nav items
document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', () => {
        document.querySelectorAll('.nav-item').forEach(i => i.classList.remove('active'));
        item.classList.add('active');
    });
});

// View toggle
document.querySelectorAll('.vt-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        btn.closest('.view-toggle').querySelectorAll('.vt-btn').forEach(b => b.classList.remove('on'));
        btn.classList.add('on');
    });
});

// Panel tabs
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

// Col filter chips
document.querySelectorAll('.col-chip').forEach(chip => {
    chip.addEventListener('click', () => {
        document.querySelectorAll('.col-chip').forEach(c => c.classList.remove('on'));
        chip.classList.add('on');
    });
});

// Card hover — show sub-count label text nicely
document.querySelectorAll('.task-card').forEach(card => {
    card.addEventListener('mouseenter', () => {
        card.style.zIndex = 10;
    });
    card.addEventListener('mouseleave', () => {
        card.style.zIndex = '';
    });
});
