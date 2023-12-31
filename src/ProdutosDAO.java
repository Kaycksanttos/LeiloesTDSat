/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
     
        //conn = new conectaDAO().connectDB();
      
        int status;
        conn = new conectaDAO().connectDB();

        try {
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) VALUES (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            status = prep.executeUpdate();
            return status;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return e.getErrorCode();
        }

    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status LIKE 'Vendido'";
        try {
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            List<ProdutosDTO> listProdutos = new ArrayList<>();

            while (resultset.next()) {
                ProdutosDTO produtosDTO = new ProdutosDTO();

                produtosDTO.setId(resultset.getInt("id"));
                produtosDTO.setNome(resultset.getString("nome"));
                produtosDTO.setValor(resultset.getInt("valor"));
                produtosDTO.setStatus(resultset.getString("status"));

                listProdutos.add(produtosDTO);
            }
            return (ArrayList<ProdutosDTO>) listProdutos;
        } catch (Exception e) {
            return null;
        }
    }

    List<ProdutosDTO> listarProdutoVendido() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
