package servlets;

import dao.EstudianteDAO;
import model.Estudiante;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/RegistroServlet")
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recibir los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String tipoDocumento = request.getParameter("tipoDocumento");
        String documento = request.getParameter("documento");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String grado = request.getParameter("grado");

        // Crear el objeto Estudiante
        Estudiante estudiante = new Estudiante(
            0, nombre, apellido, tipoDocumento, documento, telefono, correo, grado
        );

        // Insertar en base de datos
        EstudianteDAO dao = new EstudianteDAO();
        boolean registrado = dao.insertar(estudiante);

        if (registrado) {
            response.sendRedirect("registro_exitoso.html");
        } else {
            response.sendRedirect("error.html");
        }
    }
}
