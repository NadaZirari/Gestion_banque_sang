package controller;

import java.time.LocalDate;
import model.GroupeSanguin;


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
                request.getRequestDispatcher("/donneurList.jsp").forward(request, response);
                break;
        }}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String sexe = request.getParameter("sexe");
        String cin = request.getParameter("cin");
        String telephone = request.getParameter("telephone");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String poidsStr = request.getParameter("poids");
        String groupeSanguinStr = request.getParameter("groupeSanguin");

        Donneur donneur = new Donneur();
        if (idStr != null && !idStr.isEmpty()) {
            donneur.setId(Long.parseLong(idStr));
        }
        donneur.setNom(nom);
        donneur.setPrenom(prenom);
        donneur.setCin(cin);
        donneur.setSexe(sexe);
        donneur.setTelephone(telephone);

     // date de naissance et calcul automatique de l'âge
        if (dateNaissanceStr != null && !dateNaissanceStr.isEmpty()) {
            donneur.setDateNaissance(LocalDate.parse(dateNaissanceStr));
        }

        // poids
        if (poidsStr != null && !poidsStr.isEmpty()) {
            donneur.setPoids(Double.parseDouble(poidsStr));
        }

        // groupe sanguin
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

        // sauvegarde
        if (donneur.getId() == null) {
            donneurDao.save(donneur);
        } else {
            donneurDao.update(donneur);
        }

        response.sendRedirect("donneurs");}}