package modelo;

import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import pacientes.Familiar;

public class ModeloFamiliar {
    private DAOFactory dao;
    private Familiar familiar;

    public ModeloFamiliar() {
        resetModeloFamiliar();
        this.dao = new SqlServerDAOFactory(); // o MySql
    }
    
    public final void resetModeloFamiliar() {
        this.familiar = new Familiar();
    }

    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }
    
}
