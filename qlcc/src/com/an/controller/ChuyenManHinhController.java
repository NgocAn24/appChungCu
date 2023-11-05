
package com.an.controller;

import com.an.bean.DanhMucBean;

import com.an.admin.CanHoJPanel;
import com.an.view.TrangChuJPanel;

import com.an.admin.NhanVienJPanel;
import com.an.admin.XeJPanel;

import com.an.view.CaiDatJPanel;
import com.an.admin.HoTroJPanel;
import com.an.admin.HoaDonJPanel;
import com.an.view.MainJFrame;
import com.an.view.ThongTinJPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JLabel;
/**
 *
 * @author ngoca
 */

public class ChuyenManHinhController {

    private JPanel main;
    private String kindSelected = "";

    private List<DanhMucBean> listItem = null;
    private MainJFrame mainFrame; // Thêm biến mainFrame

    public ChuyenManHinhController(JPanel jpnMain) {
        this.mainFrame = mainFrame; // Gán thể hiện của MainJFrame
        this.main = jpnMain;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem) {
        kindSelected = "DangNhap";
        jpnItem.setBackground(new Color(204,204,255));
        jlbItem.setBackground(new Color(204,204,255));

        main.removeAll();
        main.setLayout(new BorderLayout());
        main.add(new TrangChuJPanel());  // màn hình khởi động ban đầu
        main.validate();
        main.repaint();
    }

    public void setEvent(List<DanhMucBean> listItem) {
        this.listItem = listItem;
        for (DanhMucBean item : listItem) {
            item.getJlb().addMouseListener(new LabelEvent(item.getKind(), item.getJpn(), item.getJlb()));
        }
    }

    class LabelEvent implements MouseListener {

        private JPanel node;
        private String kind;
        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind) {
                case "TrangChu":
                    node = new TrangChuJPanel();
                    break;
                case "Xe":
                    node = new XeJPanel();
                    break;
                case "HoaDon":
                    node = new HoaDonJPanel();
                    break;
		case "DangNhap":
                    node = new ThongTinJPanel();
                    break;
		case "NhanVien":
                    node = new NhanVienJPanel();
                    break;
		case "CaiDat":
                    node = new CaiDatJPanel();
                    break;
		case "CanHo":
                    node = new CanHoJPanel();
                    break;
		case "HoTro":
                    node = new HoTroJPanel();
                    break;
		    
                default:
                    break;
            }

            main.removeAll();
            main.setLayout(new BorderLayout());
            main.add(node);
            main.validate();
            main.repaint();
            setChangeBackgroud(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind)) {
                jpnItem.setBackground(new Color(204,204,255));
                jlbItem.setBackground(new Color(204,204,255));
            }
        }
    }

    private void setChangeBackgroud(String kind) {
        for (DanhMucBean item : listItem) {
            if (item.getKind().equalsIgnoreCase(kind)) {
                item.getJlb().setBackground(new Color(96, 100, 191));
                item.getJpn().setBackground(new Color(96, 100, 191));
            } else {
                item.getJlb().setBackground(new Color(204,204,255));
                item.getJpn().setBackground(new Color(204,204,255));
            }
        }
    }
}
