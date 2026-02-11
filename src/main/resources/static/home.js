document.addEventListener('DOMContentLoaded', () => {
    const list = document.getElementById('clearances');
    const refresh = document.getElementById('refresh');

    async function load() {
        list.textContent = 'Loading...';
        try {
            const res = await fetch('/api/clearances', { credentials: 'include' });
            if (!res.ok) throw new Error(res.status + ' ' + res.statusText);
            const data = await res.json();

            if (!Array.isArray(data) || data.length === 0) {
                list.innerHTML = '<p>No clearances found.</p>';
                return;
            }

            const table = document.createElement('table');
            const header = document.createElement('tr');
            ['ID','Student','Number','Department','Program','Purpose', 'Status', 'Date'].forEach(h => {
                const th = document.createElement('th');
                th.textContent = h;
                header.appendChild(th);
            });
            table.appendChild(header);

            data.forEach(c => {
                const tr = document.createElement('tr');
                const values = [c.clearanceId, c.studentName, c.studentNumber,c.studentDepartment, c.studentProgram, c.purposeOfClearance, c.status, c.date];
                values.forEach(v => {
                    const td = document.createElement('td');
                    td.textContent = v == null ? '' : v;
                    tr.appendChild(td);
                });
                table.appendChild(tr);
            });

            list.innerHTML = '';
            list.appendChild(table);
        } catch (e) {
            list.innerHTML = '<div class="error"><strong>Error:</strong> ' + e.toString() + '</div>';
        }
    }

    refresh.addEventListener('click', load);
    load();
});
