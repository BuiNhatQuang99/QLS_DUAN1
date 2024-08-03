/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLS.DAO;


import com.QLS.entity.LoaiSach;
import com.QLS.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class LoaiSachDao extends QLSDao<LoaiSach, String>{

    @Override
    public void insert(LoaiSach entity) {
           String sql="INSERT INTO LOAISACH (MALOAI, TENLOAI) VALUES (?, ?)";
        XJdbc.update(sql,
            entity. getMALOAI(),
            entity.getTENLOAI()
           
        );
    }

    @Override
    public void update(LoaiSach entity) {
       String sql="UPDATE LOAISACH SET TENLOAI=? WHERE MALOAI=?";
        XJdbc.update(sql,
                entity.getTENLOAI(),
                entity.getMALOAI()
        );
    }

    @Override
    public void delete(String MALOAI) {
        String sql = "DELETE * FROM LOAISACH WHERE MALOAI=?";
        XJdbc.update(sql,MALOAI );
    }

    @Override
    public LoaiSach selectById(String id) {
        String sql="SELECT * FROM LOAISACH WHERE MALOAI=?";
        List<LoaiSach> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<LoaiSach> selectAll() {
        String sql="SELECT * FROM LOAISACH";
         return this.selectBySql(sql);
    }

    @Override
    protected List<LoaiSach> selectBySql(String sql, Object... args) {
        List<LoaiSach> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                    LoaiSach ls = new LoaiSach();
                   ls.setMALOAI(rs.getInt("MALOAI"));
                   ls.setTENLOAI(rs.getString("TENLOAI"));
                   
                   list.add(ls);
                   
                   
                }
            } 
            finally{
                if(rs != null)
                    rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
}

