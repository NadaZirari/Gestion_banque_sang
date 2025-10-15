package controller;




import dao.DonneurDao;
import impl.DonneurDaoImpl;
import model.Donneur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/donneurs")
public class DonneurServlet extends HttpServlet {

    private DonneurDao donneurDao = new DonneurDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "new":
                request.getRequestDispatcher("/donneur-form.jsp").forward(request, response);
                break;
            case "edit":
                Long idEdit = Long.parseLong(request.getParameter("id"));
                Donneur donneurEdit = donneurDao.findById(idEdit);
                request.setAttribute("donneur", donneurEdit);
                request.getRequestDispatcher("/donneur-form.jsp").forward(request, response);
                break;
            case "delete":
                Long idDelete = Long.parseLong(request.getParameter("id"));
                donneurDao.delete(idDelete);
                response.sendRedirect("donneurs");
                break;
            case "list":
            default:
                List<Donneur> donneurs = donneurDao.findAll();
                request.setAttribute("donneurs", donneurs);
                request.getRequestDispatcher("/donneur-list.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cin = request.getParameter("cin");
        String telephone = request.getParameter("telephone");

        Donneur donneur = new Donneur();
        if (idStr != null && !idStr.isEmpty()) {
            donneur.setId(Long.parseLong(idStr));
        }
        donneur.setNom(nom);
        donneur.setPrenom(prenom);
        donneur.setCin(cin);
        donneur.setTelephone(telephone);

        if (donneur.getId() == null) {
            donneurDao.save(donneur);
        } else {
            donneurDao.update(donneur);
        }

        response.sendRedirect("donneurs");
    }
}
