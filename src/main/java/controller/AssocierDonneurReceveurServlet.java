package controller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;
import service.DonneurService;

import model.Donneur;
import model.Receveur;
import service.AssociationService;
import service.ReceveurService;

public class AssocierDonneurReceveurServlet extends HttpServlet {

    private final DonneurService donneurService = new DonneurService();
    private final ReceveurService receveurService = new ReceveurService();
    private final AssociationService associationService = new AssociationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Donneur> donneurs = donneurService.listerDisponibles();
        List<Receveur> receveurs = receveurService.listerNonSatisfaits();

        req.setAttribute("donneurs", donneurs);
        req.setAttribute("receveurs", receveurs);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/associerDonneurReceveur.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long idDonneur = Long.parseLong(req.getParameter("donneurId"));
        Long idReceveur = Long.parseLong(req.getParameter("receveurId"));

        boolean succes = associationService.associerDonneurReceveur(idDonneur, idReceveur);

        if (succes) {
            req.setAttribute("message", "OK associéé !");
        } else {
            req.setAttribute("erreur", " :/ Vérifie la compatibilité ou la disponibilité.");
        }

        doGet(req, resp); // recharge la page avec les listes à jour
    }
}
