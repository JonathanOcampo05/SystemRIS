// Validación de inicio de sesión
function checkLogin() {
    const token = localStorage.getItem('jwt_token');
    if (!token) {
        window.location.href = 'http://127.0.0.1:5500/Frontend_RIS/noAuth.html';  
        return;
    }
}
checkLogin();

// Registrar paciente
document.getElementById("registroPaciente").addEventListener("submit", async function (event) {
    event.preventDefault(); 

    // Recoger insumos seleccionados
    const insumosSeleccionados = Array.from(document.querySelectorAll(".checkbox-group input:checked"))
        .map(input => input.value)
        .join(", ");
    document.getElementById("insumos").value = insumosSeleccionados;

    // Capturar los datos del formulario
    const name = document.getElementById("nombre").value;
    const age = parseInt(document.getElementById("edad").value);
    const email = document.getElementById("correo").value;
    const shift = parseInt(document.getElementById("turno").value);
    const symptoms = document.getElementById("sintomas").value;
    const doctor = document.getElementById("doctor").value;
    const supplies = document.getElementById("insumos").value;

    const paciente = {
        name: name,
        age: age,
        email: email,
        shift: shift,
        symptoms: symptoms,
        supplies: supplies,
        doctor: doctor,
    };

    try {
        const response = await fetch("http://localhost:8080/patients/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(paciente),
        });

        if (response.ok) {
            const mensaje = await response.json();
            alert(mensaje.message || "Paciente registrado exitosamente"); 
            document.getElementById("registroPaciente").reset(); 
        } else {
            const error = await response.json();
            alert(error.message || "Error al registrar el paciente");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Hubo un problema al conectar con el servidor");
    }
});

// Cargar pacientes activos en la tabla
async function cargarPacientes() {
    try {
        const response = await fetch("http://localhost:8080/patients/pacientesActivos");
        const pacientes = await response.json();
        
        const tablaPacientes = document.getElementById("tablaPacientes");
        tablaPacientes.innerHTML = ''; // Limpiar la tabla antes de agregar datos

        pacientes.forEach(paciente => {
            if (paciente.status === "activo") {
                const row = tablaPacientes.insertRow();
                row.innerHTML = `
                    <td>${paciente.name}</td>
                    <td>${paciente.age}</td>
                    <td>
                        <button class="btn btn-primary" onclick="editarPaciente(${paciente.id})">Editar</button>
                        <button class="btn btn-danger" onclick="eliminarPaciente(${paciente.id})">Eliminar</button>
                    </td>
                `;
            }
        });
    } catch (error) {
        console.error("Error al cargar los pacientes:", error);
    }
}

// Mostrar modal de edición
function editarPaciente(id) {
    // Obtener los datos del paciente (puedes hacer un fetch aquí si necesitas obtener datos específicos)
    const paciente = obtenerPacientePorId(id);
    
    // Rellenar los campos del formulario con los datos del paciente
    document.getElementById("nombreEditar").value = paciente.name;
    document.getElementById("edadEditar").value = paciente.age;
    document.getElementById("correoEditar").value = paciente.email;
    document.getElementById("turnoEditar").value = paciente.shift;
    document.getElementById("sintomasEditar").value = paciente.symptoms;
    document.getElementById("doctorEditar").value = paciente.doctor;
    document.getElementById("idPacienteEditar").value = paciente.id;

    // Mostrar el modal
    $('#editarPacienteModal').modal('show');
}

// Obtener paciente por ID (esto depende de la forma en que recibas los datos)
async function obtenerPacientePorId(id) {
    try {
        const response = await fetch(`http://localhost:8080/patients/${id}`);
        return await response.json();
    } catch (error) {
        console.error("Error al obtener el paciente:", error);
    }
}

// Guardar cambios al editar paciente
document.getElementById("editarPaciente").addEventListener("submit", async function (event) {
    event.preventDefault();

    const id = document.getElementById("idPacienteEditar").value;
    const name = document.getElementById("nombreEditar").value;
    const age = parseInt(document.getElementById("edadEditar").value);
    const email = document.getElementById("correoEditar").value;
    const shift = parseInt(document.getElementById("turnoEditar").value);
    const symptoms = document.getElementById("sintomasEditar").value;
    const doctor = document.getElementById("doctorEditar").value;

    const paciente = {
        id: id,
        name: name,
        age: age,
        email: email,
        shift: shift,
        symptoms: symptoms,
        doctor: doctor,
    };

    try {
        const response = await fetch("http://localhost:8080/patients/update", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(paciente),
        });

        if (response.ok) {
            alert("Paciente actualizado correctamente");
            $('#editarPacienteModal').modal('hide'); // Cerrar modal
            cargarPacientes(); // Recargar tabla
        } else {
            const error = await response.json();
            alert(error.message || "Error al actualizar el paciente");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Hubo un problema al conectar con el servidor");
    }
});

// Eliminar paciente (cambiar estado)
async function eliminarPaciente(id) {
    try {
        const response = await fetch("http://localhost:8080/patients/change-status", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ id: id })
        });

        if (response.ok) {
            alert("Paciente eliminado (estado cambiado) correctamente");
            cargarPacientes(); // Recargar tabla
        } else {
            const error = await response.json();
            alert(error.message || "Error al eliminar el paciente");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("Hubo un problema al conectar con el servidor");
    }
}

// Inicializar la carga de pacientes cuando se cargue la página
document.addEventListener("DOMContentLoaded", cargarPacientes);
