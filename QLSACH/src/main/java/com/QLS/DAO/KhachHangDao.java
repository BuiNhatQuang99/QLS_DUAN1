/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLS.DAO;

import com.QLS.entity.KhachHang;

import com.QLS.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author ACER
 */
public class KhachHangDao extends QLSDao<KhachHang, String>{

    @Override
    public void insert(KhachHang entity) {
         String sql="INSERT INTO KHACHHANG (MAKH, HOTEN, SDT, EMAIL, NGAYDK) VALUES (?, ?, ?, ?, ?)";
        XJdbc.update(sql,
            entity. getMAKH(),
            entity.getHOTEN(),
            entity.getSDT(),
            entity.getEMAIL(),
            entity.getNGAYDK()
        );
    }

    @Override
    public void update(KhachHang entity) {
        String sql="UPDATE KHACHHANG SET HOTEN=?, SDT=?, EMAIL=?, NGAYDK=? WHERE MAKH=?";
        XJdbc.update(sql,
                entity.getHOTEN(),
                entity.getSDT(),
                entity.getEMAIL(),
                entity.getNGAYDK(),
                entity.getMAKH()
        );
        
    }

    @Override
    public void delete(String MAKH) {
       String sql = "DELETE * FROM KHACHHANG WHERE MAKH=?";
        XJdbc.update(sql,MAKH );
    }

    @Override
    public KhachHang selectById(String id) {
       String sql="SELECT * FROM KHACHHANG WHERE MAKH=?";
        List<KhachHang> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhachHang> selectAll() {
        String sql="SELECT * FROM KHACHHANG";
         return this.selectBySql(sql);
    }

    @Override
    protected List<KhachHang> selectBySql(String sql, Object... args) {
          List<KhachHang> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                   KhachHang kh = new KhachHang();
                   kh.setMAKH(rs.getInt("MAKH"));
                   kh.setHOTEN(rs.getString("HOTEN"));
                   kh.setSDT(rs.getString("SDT"));
                   kh.setEMAIL(rs.getString("EMAIL"));
                   kh.setNGAYDK(rs.getString("NGAYDK"));
                   list.add(kh);
                   
                   
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

