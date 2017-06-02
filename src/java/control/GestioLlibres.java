package control;

import java.io.*;
import java.sql.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import model.*;
import persistencia.*;

public class GestioLlibres extends HttpServlet {

    private Connection con;
    private ConfiguracioConnexio dbCon;

    @Override
    public void init() throws ServletException {
        super.init();
        dbCon = new ConfiguracioConnexio(this.getInitParameter("driver"),
                this.getInitParameter("cadenaConnexioInt"),
                this.getInitParameter("usuari"), this.getInitParameter("contrasenya"));
        con = dbCon.getConnexio();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        LlibreDao dao = new LlibreDao(con);

        String action = request.getParameter("accio");
        switch (action) {
            case "afegir":
                String resposta = afegirLLibre(request, response);
                request.setAttribute("afegit", resposta);
                anarAPagina("afegir.jsp", request, response);
                break;
            case "cercar":
                Llibre llibre = cercaLlibreIsbn(request, response);
                request.setAttribute("cercat", llibre);
                anarAPagina("modifica.jsp", request, response);
                break;
            case "cercarTitol":
                Llibre llibreTitol = cercaLlibreTitol(request, response);
                request.setAttribute("cercatTitol", llibreTitol);
                anarAPagina("cercaTitol.jsp", request, response);
                break;
            case "eliminar":
                String resposta2 = eliminar(request, response);
                request.setAttribute("eliminat", resposta2);
                anarAPagina("elimina.jsp", request, response);
                break;
            case "modificar":
                String resposta3 = modificar(request, response);
                request.setAttribute("modificat", resposta3);
                anarAPagina("modifica.jsp", request, response);
                break;
            case "cercarTots":
                List<Llibre> resposta4 = cercaTots(request, response);
                request.setAttribute("cercatTots", resposta4);
                anarAPagina("cercaTots.jsp", request, response);
                break;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void destroy() {
        dbCon.tancar();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.        
    }

    /**
     * Redirecciona la petició(Request) a una altra pàgina
     *
     * @param pagina
     * @param req
     * @param res
     * @throws IOException
     * @throws ServletException
     */
    private void anarAPagina(String pagina, HttpServletRequest req, HttpServletResponse res)
            throws IOException, ServletException {

        RequestDispatcher dispatcher = req.getRequestDispatcher(pagina);
        if (dispatcher != null) {
            dispatcher.forward(req, res);
        }
    }

    private String afegirLLibre(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        LlibreDao dao = null;
        String isbn, titol, autor, editorial, any, estok;
        int anyEdicio, estoc;
        String resposta;
        if (!(isbn = req.getParameter("isbn_")).matches("[0-9]{13}")) {
            resposta = "ISBN incorrecte, ha d'estar format per 13 dígits";
        } else if (!(any = req.getParameter("anyEdicio_")).matches("^[1-9][0-9]{1,3}")) {
            resposta = "Any d'edició incorrecte, ha de ser any entre 1000-2999";
        } else if (!(estok = req.getParameter("estoc_")).matches("[0-9]{1,3}")) {
            resposta = "Estoc incorrecte";
        } else if ((titol = req.getParameter("titol_")) == null
                || (autor = req.getParameter("autor_")) == null
                || (editorial = req.getParameter("editorial_")) == null) {
            resposta = "s'han d'emplenar tots els camps";
        } else {
            anyEdicio = Integer.parseInt(any);
            estoc = Integer.parseInt(estok);

            dao = new LlibreDao(con);
            resposta = dao.afegir(new Llibre(isbn, titol, autor, editorial, anyEdicio, estoc)) ? "El llibre s'ha afegit correctament" : "El llibre no s'ha pogut afegir.";
        }
        return resposta;
    }

    private Llibre cercaLlibreIsbn(HttpServletRequest request, HttpServletResponse response) {
        LlibreDao dao = null;
        String isbn;
        dao = new LlibreDao(con);
        Llibre resposta;
        if (!(isbn = request.getParameter("isbn_")).matches("[0-9]{13}")) {
            resposta = null;
        } else {
            resposta = dao.cercarPerISBN(isbn);
        }
        return resposta;
    }

    private Llibre cercaLlibreTitol(HttpServletRequest request, HttpServletResponse response) {
        LlibreDao dao = null;
        String titol;
        dao = new LlibreDao(con);
        Llibre resposta;
        if ((titol = request.getParameter("titol_")) == null) {
            resposta = null;
        } else {
            resposta = dao.cercarPerTitol(titol);
        }
        return resposta;
    }

    private String eliminar(HttpServletRequest request, HttpServletResponse response) {
        LlibreDao dao = null;
        String isbn;
        dao = new LlibreDao(con);
        String resposta;
        if (!(isbn = request.getParameter("isbn_")).matches("[0-9]{13}")) {
            resposta = "ISBN incorrecte, ha d'estar format per 13 dígits";
        } else {
            resposta = dao.eliminar(isbn) ? "El llibre s'ha pogut eliminar" : "El llibre no s'ha pogut eliminar";
        }
        return resposta;
    }

    private String modificar(HttpServletRequest req, HttpServletResponse response) {
        LlibreDao dao = null;
        String isbn, titol, autor, editorial, any, estok;
        int anyEdicio, estoc;
        dao = new LlibreDao(con);
        String resposta;
        if (!(isbn = req.getParameter("isbn_")).matches("[0-9]{13}")) {
            resposta = "ISBN incorrecte, ha d'estar format per 13 dígits";
        } else if (!(any = req.getParameter("anyEdicio_")).matches("^[1-9][0-9]{1,3}")) {
            resposta = "Any d'edició incorrecte, ha de ser any entre 1000-2999";
        } else if (!(estok = req.getParameter("estoc_")).matches("[0-9]{1,3}")) {
            resposta = "Estoc incorrecte";
        } else if ((titol = req.getParameter("titol_")) == null
                || (autor = req.getParameter("autor_")) == null
                || (editorial = req.getParameter("editorial_")) == null) {
            resposta = "s'han d'emplenar tots els camps";
        } else {
            anyEdicio = Integer.parseInt(any);
            estoc = Integer.parseInt(estok);

            dao = new LlibreDao(con);
            resposta = dao.modificar(new Llibre(isbn, titol, autor, editorial, anyEdicio, estoc)) ? "El llibre s'ha modificat correctament" : "El llibre no s'ha pogut modificar.";
        }
        return resposta;
    }
    
    private List<Llibre> cercaTots(HttpServletRequest req, HttpServletResponse response) {
        LlibreDao dao = new LlibreDao(con);
        return dao.cercarTots();
    }
}
