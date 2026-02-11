const debugDiv = document.getElementById('debug');
const messageDiv = document.getElementById('message');
const submitBtn = document.getElementById('submitBtn');
const departmentPrograms = {
    SOIT: [
        "Computer Science",
        "Information Technology",
        "Entertainment and Media Computing",
        "Information Systems",
        "Data Science"
    ],
    SOHS: [
        "Biology",
        "Medical Technology",
        "Pharmacy",
        "Physical therapy",
        "Pyschology",
        "Radiologic Technology",
    ],
    SON: [
        "Nursing"
    ],
    ETYSB: [
        "Business Administration",
        "Accountancy",
        "Business Analytics with AI",
        "Entrepreneurship",
        "Financial Management and Technology",
        "International Business",
        "Real Estate Management",
    ],
    SOMDA: [
        "Broadcast Media",
        "Digital Film",
        "Multimedia Arts",
        "MMA and Broadcast Media",
        "MMA and Digital Journalism",
    ]
};

function populatePrograms() {
    const departmentSelect = document.getElementById("studentDepartment");
    const programSelect = document.getElementById("studentProgram");
    const selectedDepartment = departmentSelect.value;

    // Clear previous options
    programSelect.innerHTML = '<option value="">Select a program</option>';

    if (selectedDepartment && departmentPrograms[selectedDepartment]) {
        const programs = departmentPrograms[selectedDepartment];
        programs.forEach(function(program) {
            const option = document.createElement("option");
            option.value = program;
            option.textContent = program;
            programSelect.appendChild(option);
        });
        programSelect.disabled = false;
    } else {
        programSelect.disabled = true;
    }
}
function showDebug(info) {
    debugDiv.style.display = 'block';
    debugDiv.textContent = JSON.stringify(info, null, 2);
}

document.getElementById('clearanceForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    // Disable submit button
    submitBtn.disabled = true;
    submitBtn.textContent = 'Submitting...';
    
    // Clear previous messages
    messageDiv.textContent = '';
    messageDiv.className = '';
    
    const formData = {
        studentName: document.getElementById('studentName').value,
        studentNumber: parseInt(document.getElementById('studentNumber').value, 10),
        studentDepartment: document.getElementById('studentDepartment').value,
        studentProgram: document.getElementById('studentProgram').value,
        purposeOfClearance: document.getElementById('purposeOfClearance').value,
        status: "Pending"
    };

    showDebug({
        action: 'Sending request',
        url: window.location.origin + '/api/clearances',
        method: 'POST',
        data: formData
    });

    try {
        const response = await fetch('/api/clearances', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            credentials: 'include',
            body: JSON.stringify(formData)
        });

        showDebug({
            action: 'Response received',
            status: response.status,
            statusText: response.statusText,
            ok: response.ok,
            headers: Object.fromEntries(response.headers.entries())
        });

        if (response.ok) {
            const result = await response.json();
            messageDiv.className = 'message success';
            messageDiv.textContent = '✓ Clearance request submitted successfully!';
            document.getElementById('clearanceForm').reset();
            
            showDebug({
                action: 'Success',
                result: result
            });
        } else {
            const errorText = await response.text();
            messageDiv.className = 'message error';
            messageDiv.textContent = `Error (${response.status}): ${response.statusText}`;
            
            showDebug({
                action: 'Error response',
                status: response.status,
                errorText: errorText
            });
        }
    } catch (error) {
        messageDiv.className = 'message error';
        messageDiv.textContent = '⚠ Network error. Is the backend server running?';
        
        showDebug({
            action: 'Exception caught',
            errorName: error.name,
            errorMessage: error.message,
            error: error.toString()
        });
    } finally {
        // Re-enable submit button
        submitBtn.disabled = false;
        submitBtn.textContent = 'Submit Request';
    }
});

// Test backend connection on page load
window.addEventListener('load', async () => {
    try {
        const response = await fetch('/api/clearances');
        if (response.ok) {
            console.log('✓ Backend connection successful');
        } else {
            console.warn('⚠ Backend returned status:', response.status);
        }
    } catch (error) {
        console.error('✗ Cannot connect to backend:', error.message);
        messageDiv.className = 'message error';
        messageDiv.textContent = '⚠ Warning: Cannot connect to backend server. Make sure it\'s running.';
    }
});
