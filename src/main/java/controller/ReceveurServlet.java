package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

import model.Receveur;
import model.GroupeSanguin;
import service.ReceveurService;

public class ReceveurServlet extends HttpServlet {

    private ReceveurService receveurService = new ReceveurService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null || action.equals("list")) {
            List<Receveur> receveurs = receveurService.listerReceveurs();
            req.setAttribute("receveurs", receveurs);
            req.getRequestDispatcher("/listeReceveurs.jsp").forward(req, resp);

        } else if (action.equals("add")) {
            req.getRequestDispatcher("/ajouterReceveur.jsp").forward(req, resp);

        } else if (action.equals("delete")) {
            Long id = Long.parseLong(req.getParameter("id"));
            receveurService.supprimerReceveur(id);
            resp.sendRedirect("receveurs?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String cin = req.getParameter("cin");
        String telephone = req.getParameter("telephone");
        String sexe = req.getParameter("sexe");
        GroupeSanguin groupe = GroupeSanguin.valueOf(req.getParameter("groupeSanguin"));
        Receveur.Priorite priorite = Receveur.Priorite.valueOf(req.getParameter("priorite"));

        Receveur receveur = new Receveur();
        receveur.setNom(nom);
        receveur.setPrenom(prenom);
        receveur.setCin(cin);
        receveur.setTelephone(telephone);
        receveur.setSexe(sexe);
        receveur.setGroupeSanguin(groupe);
        receveur.setPriorite(priorite);

        receveurService.ajouterReceveur(receveur);
        resp.sendRedirect("receveurs?action=list");
    }
}
