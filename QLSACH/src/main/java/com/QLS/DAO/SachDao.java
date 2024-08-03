/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.QLS.DAO;


import com.QLS.entity.KhachHang;
import com.QLS.entity.Sach;
import com.QLS.utils.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class SachDao extends QLSDao<Sach, String> {

    @Override
    public void insert(Sach entity) {
            String sql="INSERT INTO SACH (MASACH, TENSACH, LOAISACH, TACGIA, SOLUONG, GIA, NGAYXUATBAN, MOTA, MANV) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
            entity. getMASACH(),
            entity.getTENSACH(),
            entity.getLOAISACH(),
            entity.getTACGIA(),
            entity.getSOLUONG(),
            entity.getGIA(),
            entity.getNGAYXUATBAN(),
            entity.getMOTA(),
            entity.getMANV()
        );
    }

    @Override
    public void update(Sach entity) {
        String sql="UPDATE KHACHHANG SET TENSACH=?, LOAISACH=?, TACGIA=?, SOLUONG=?, GIA=?, NGAYXUATBAN=?, MOTA=?, MANV=? WHERE MASACH=?";
        XJdbc.update(sql,
                entity.getTENSACH(),
                entity.getLOAISACH(),
                entity.getTACGIA(),
                entity.getSOLUONG(),
                entity.getGIA(),
                entity.getNGAYXUATBAN(),
                entity.getMOTA(),
                entity.getMANV(),
                entity.getMASACH()
        );
    }

    @Override
    public void delete(String MASACH) {
         String sql = "DELETE * FROM SACH WHERE MASACH=?";
        XJdbc.update(sql,MASACH );
    }

    @Override
    public Sach selectById(String id) {
        String sql="SELECT * FROM KHACHHANG WHERE MAKH=?";
        List<Sach> list = this.selectBySql(sql, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Sach> selectAll() {
         String sql="SELECT * FROM SACH";
         return this.selectBySql(sql);
    }

    @Override
    protected List<Sach> selectBySql(String sql, Object... args) {
           List<Sach> list=new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = XJdbc.query(sql, args);
                while(rs.next()){
                   Sach s = new Sach();
                   s.setMASACH(rs.getString("MASACH"));
                   s.setTENSACH(rs.getString("TENSACH"));
                   s.setLOAISACH(rs.getInt("LOAISACH"));
                   s.setTACGIA(rs.getString("TACGIA"));
                   s.setSOLUONG(rs.getInt("SOLUONG"));
                   s.setGIA(rs.getFloat("GIA"));
                   s.setNGAYXUATBAN(rs.getInt("NGAYXUATBAN"));
                   s.setMOTA(rs.getString("MOTA"));
                   s.setMANV(rs.getString("MANV"));
                   list.add(s);
                   
                   
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
