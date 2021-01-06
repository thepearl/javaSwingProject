package GestionnaireDeCommandes;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class FenetreGestionnaire extends JFrame
{
    public JMenu firstMenu;
    public JMenu secondMenu;
    public JMenuItem i1, i2, i3, i4, i5, i6;
    JTree directoryTree;

    public FenetreGestionnaire() throws IOException {
        JMenuBar menuBar = new JMenuBar();
        firstMenu = new JMenu("Fichier");
        secondMenu = new JMenu("Article");

        i1 = new JMenuItem("Importer");
        i2 = new JMenuItem("Exporter");
        i3 = new JMenuItem("Quitter");
        firstMenu.add(i1);
        firstMenu.add(i2);
        firstMenu.add(i3);

        i4 = new JMenuItem("Nouveau");
        i5 = new JMenuItem("Ajouter");
        i6 = new JMenuItem("Supprimer");
        secondMenu.add(i4);
        secondMenu.add(i5);
        secondMenu.add(i6);

        menuBar.add(firstMenu);
        menuBar.add(secondMenu);

        JPanel addArticlePanel = new JPanel();
        addArticlePanel.setBackground(Color.red);

        JPanel deleteArticlePanel = new JPanel();
        deleteArticlePanel.setBackground(Color.blue);

        directoryTree = new JTree(read());

        JList articlesList = new JList();

        add(addArticlePanel);
        add(articlesList);
        add(deleteArticlePanel);
        add(directoryTree);


        // Set bar items listeners :
        i1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    directoryTree = new JTree(read());
                    directoryTree.setVisible(true);
                    add(new JScrollPane(directoryTree));
                    System.out.println("Succ: Loaded file with success");
                } catch (IOException ioException) {
                    System.out.println("ERROR: Failed to load file");
                    ioException.printStackTrace();
                }
            }
        });

        i2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        i3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        i4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        i5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });

        i6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });


        setVisible(true);
        setJMenuBar(menuBar);
        setVisible(true);
        setSize(800,800);
        setLayout(new GridLayout(2,2));

    }

    public DefaultMutableTreeNode read() throws IOException {
        DefaultMutableTreeNode menu = new DefaultMutableTreeNode("menu");
        DefaultMutableTreeNode article = null;

        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showDialog(FenetreGestionnaire.this, "Importer");
        File file;
            if (returnVal == JFileChooser.APPROVE_OPTION) {
        file = chooser.getSelectedFile();
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        String strLine;
        String[] result;

        while ((strLine = buffer.readLine()) != null) {
            result = strLine.split(":");
            for (int i = 0; i < result.length; i++) {
                if (i == 0) {
                    article = new DefaultMutableTreeNode(result[i]);
                } else {
                    article.add(new DefaultMutableTreeNode(result[i]));
                }
                menu.add(article);
            }
        }
        buffer.close();
        return (menu);
    }
            else
        {
            return new DefaultMutableTreeNode();
        }
    }

}
