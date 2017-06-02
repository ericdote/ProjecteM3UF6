package persistencia;

import java.io.*;
import java.sql.*;
import java.util.*;
import model.*;

public class LlibreDao {

    private Connection con;

    public LlibreDao(Connection con) {
        this.con = con;
    }
    /**
     * Metode que permeteix afegir un llibre a la BBDD.
     * @param llib
     * @return 
     */
    public boolean afegir(Llibre llib) {
        boolean afegit = true;
        PreparedStatement pt = null;
        String sentencia = "INSERT INTO LLIBRE_M3(ISBN,TITOL,AUTOR,EDITORIAL,ANYEDICIO,ESTOC)"
                + " VALUES(?,?,?,?,?,?)";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, llib.getIsbn());
            pt.setString(2, llib.getTitol());
            pt.setString(3, llib.getAutor());
            pt.setString(4, llib.getEditorial());
            pt.setInt(5, llib.getAnyEdicio());
            pt.setInt(6, llib.getEstoc());

            if (pt.executeUpdate() == 0) {
                afegit = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        } finally {
            tancarRecurs(pt);
        }

        return afegit;
    }
    /**
     * Metode que elimina un llibre de la BBDD pel seu ISBN.
     * @param isbn
     * @return 
     */
    public boolean eliminar(String isbn) {
        boolean afegit = true;
        PreparedStatement pt = null;
        String sentencia = "DELETE FROM LLIBRE_M3 WHERE ISBN = ?";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, isbn);

            if (pt.executeUpdate() == 0) {
                afegit = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            afegit = false;
        } finally {
            tancarRecurs(pt);
        }

        return afegit;
    }
    /**
     * Metode que modifica un llibre a la BBDD.
     * @param llibre
     * @return 
     */
    public boolean modificar(Llibre llibre) {
        boolean modificat = true;
        PreparedStatement pt = null;
        String sentencia = "UPDATE LLIBRE_M3 SET TITOL = ?, AUTOR = ?, EDITORIAL = ?, ANYEDICIO = ?, ESTOC = ? WHERE ISBN = ?";
        try {
            pt = con.prepareStatement(sentencia);
            pt.setString(1, llibre.getTitol());
            pt.setString(2, llibre.getAutor());
            pt.setString(3, llibre.getEditorial());
            pt.setInt(4, llibre.getAnyEdicio());
            pt.setInt(5, llibre.getEstoc());
            pt.setString(6, llibre.getIsbn());

            if (pt.executeUpdate() == 0) {
                modificat = false;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            modificat = false;
        } finally {
            tancarRecurs(pt);
        }
        return modificat;
    }
    /**
     * Metode que cerca un llibre pel seu ISBN.
     * @param isbn
     * @return 
     */
    public Llibre cercarPerISBN(String isbn) {
        String consulta = "SELECT * FROM LLIBRE_M3 WHERE ISBN='" + isbn + "'";
        Statement st;
        ResultSet rs;
        Llibre llib = null;

        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                llib = new Llibre(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llib;
    }
    /**
     * Metode que cerca un llibre pel titol.
     * @param titol
     * @return 
     */
    public Llibre cercarPerTitol(String titol){
        String consulta = "SELECT * FROM LLIBRE_M3 WHERE TITOL='" + titol + "'";
        Statement st;
        ResultSet rs;
        Llibre llib = null;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            if (rs.next()) {
                llib = new Llibre(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getInt(6));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llib;
    }
    /**
     * Metode que cerca tots els llibres.
     * @return 
     */
    public List<Llibre> cercarTots() {
        String consulta = " SELECT * FROM LLIBRE_M3";
        Statement st;
        ResultSet rs;
        List<Llibre> llista = new ArrayList<>();
        try {
            st = con.createStatement();
            rs = st.executeQuery(consulta);
            while (rs.next()) {
                llista.add(new Llibre(rs.getString(1),
                        rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6)));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return llista;
    }
    /**
     * Metode que tanca el recurs.
     * @param r 
     */
    private void tancarRecurs(AutoCloseable r) {
        try {
            r.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {

        }

    }

}
