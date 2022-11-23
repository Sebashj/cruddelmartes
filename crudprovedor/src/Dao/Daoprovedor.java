package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Provedor;

public class Daoprovedor {
	Conexion cx =null;
	
	public Daoprovedor() {
		cx=new Conexion();
	}
	
	public boolean insertarprovedor(Provedor user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Provedor VALUES(null,?,?,?,?)");
			ps.setString(1, user.getMarca());
			ps.setString(2, user.getModelo());
			ps.setDouble(3, user.getPrecio());
			ps.setInt(4, user.getCantidad());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Provedor> fetchprovedors(){
		ArrayList<Provedor> lista=new ArrayList<Provedor>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Provedor");
			rs=ps.executeQuery();
			while(rs.next()) {
				Provedor u=new Provedor();
				u.setIdprovedor(rs.getInt("Idprovedor"));
				u.setMarca(rs.getString("Marca"));
				u.setModelo(rs.getString("Modelo"));
				u.setPrecio(rs.getDouble("Precio"));
				u.setCantidad(rs.getInt("Cantidad"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	public boolean eliminarprovedor(int idprovedor) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Provedor WHERE idprovedor=?");
			ps.setInt(1, idprovedor);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarprovedor(Provedor user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Provedor SET marca=?, modelo=?, precio=?, cantidad=?  WHERE idprovedor=?");
			ps.setString(1, user.getMarca());
			ps.setString(2, user.getModelo());
			ps.setDouble(3, user.getPrecio());
			ps.setInt(4, user.getCantidad());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}

