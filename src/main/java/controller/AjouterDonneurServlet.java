package controller;


import dao.DonneurDao;
import impl.DonneurDaoImpl;
import model.Donneur;
import model.GroupeSanguin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@WebServlet("/ajouter-donneur")
public class AjouterDonneurServlet extends HttpServlet {

    private DonneurDao donneurDao = new DonneurDaoImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Affiche la page du formulaire
        request.getRequestDispatcher("/ajouterDonneur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupère les données du formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        String cin = request.getParameter("cin");
        String telephone = request.getParameter("telephone");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String poidsStr = request.getParameter("poids");
        String groupeSanguinStr = request.getParameter("groupeSanguin");

        // Crée un nouvel objet Donneur
        Donneur donneur = new Donneur();
        donneur.setNom(nom);
        donneur.setPrenom(prenom);
        donneur.setSexe(sexe);
        donneur.setCin(cin);
        donneur.setTelephone(telephone);

       
           
            if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
                donneur.setDateNaissance(LocalDate.parse(dateNaissanceStr));
            }

            

        // Conversion du poids
        if (poidsStr != null && !poidsStr.isEmpty()) {
            donneur.setPoids(Double.parseDouble(poidsStr));
        }

        // Conversion du groupe sanguin
        GroupeSanguin groupe = null;
        switch (groupeSanguinStr) {
            case "O+": groupe = GroupeSanguin.O_POS; break;
            case "O-": groupe = GroupeSanguin.O_NEG; break;
            case "A+": groupe = GroupeSanguin.A_POS; break;
            case "A-": groupe = GroupeSanguin.A_NEG; break;
            case "B+": groupe = GroupeSanguin.B_POS; break;
            case "B-": groupe = GroupeSanguin.B_NEG; break;
            case "AB+": groupe = GroupeSanguin.AB_POS; break;
            case "AB-": groupe = GroupeSanguin.AB_NEG; break;
        }
        donneur.setGroupeSanguin(groupe);

        // Sauvegarde
        donneurDao.save(donneur);

        // Redirection vers la liste
        response.sendRedirect(request.getContextPath() + "/donneurs");
}}}