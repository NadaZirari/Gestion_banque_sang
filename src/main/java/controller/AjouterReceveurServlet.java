package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

import model.Receveur;
import model.GroupeSanguin;
import service.ReceveurService;

public class AjouterReceveurServlet extends HttpServlet {

    private ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/ajouterReceveur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cin = request.getParameter("cin");
        String telephone = request.getParameter("telephone");
        String sexe = request.getParameter("sexe");
        String groupeSanguin = request.getParameter("groupeSanguin");
        String priorite = request.getParameter("priorite");

        GroupeSanguin groupe = GroupeSanguin.valueOf(groupeSanguin);
        Receveur.Priorite prioriteEnum = Receveur.Priorite.valueOf(priorite);

        Receveur receveur = new Receveur();
        receveur.setNom(nom);
        receveur.setPrenom(prenom);
        receveur.setCin(cin);
        receveur.setTelephone(telephone);
        receveur.setSexe(sexe);
        receveur.setGroupeSanguin(groupe);
        receveur.setPriorite(prioriteEnum);

        receveurService.ajouterReceveur(receveur);

        response.sendRedirect(request.getContextPath() + "/receveurs");
    }
}
