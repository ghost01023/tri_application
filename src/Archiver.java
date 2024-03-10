import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.List;
import java.util.zip.*;
public class Archiver {
    static final Font font1 = new Font("Anonymous Pro", Font.BOLD, 36);
    public static void main(String[] args) {
        JFrame archiveMain = new JFrame("Archiver");
        archiveMain.setDefaultCloseOperation(archiveMain.DISPOSE_ON_CLOSE);
        archiveMain.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                AssignmentII.main(new String[]{});
                archiveMain.dispose();
            }
        });
        archiveMain.setSize(500, 500);
        archiveMain.setLayout(null);
        JButton archiveButton = new JButton("ARCHIVE FILES");
        archiveButton.setFont(font1);
        archiveButton.setBounds(0, 150, 500, 70);
        JButton extractButton = new JButton("EXTRACT FILES");
        extractButton.setFont(font1);
        extractButton.setBounds(0, 250, 500, 70);
        archiveMain.add(archiveButton);
        archiveButton.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent evt) {
                try {
                    evt.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles =
                            (List<File>) evt.getTransferable()
                                    .getTransferData(
                                            DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        //ARCHIVE EACH FILE INTO THE ZIP FILE
                        archiveFile(file.getAbsolutePath(),
                                "C:\\Users\\Rosja" +
                                " Dostoyevsjky\\" + "Documents\\IntelliJ " +
                                "IDEA\\Assignment-II\\" + "src\\text.gz");
                    }
                } catch (IOException | UnsupportedFlavorException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        archiveMain.add(extractButton);
        extractButton.setDropTarget(new DropTarget() {
            public synchronized void drop(DropTargetDropEvent event) {
                try {
                    event.acceptDrop(DnDConstants.ACTION_COPY);
                    List<File> droppedFiles =
                            (List<File>) event.getTransferable()
                                    .getTransferData(
                                            DataFlavor.javaFileListFlavor);
                    for (File file : droppedFiles) {
                        extractGzipFile(file.getAbsolutePath(),
                                "C:\\Users" +
                                "\\Rosja Dostoyevsjky\\" + "Documents" +
                                "\\IntelliJ IDEA\\Assignment-II\\src\\extract" +
                                ".txt");
                    }
                } catch (IOException | UnsupportedFlavorException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        archiveMain.setVisible(true);
    }

    public static void archiveFile(String inputFilePath,
                                   String outputFilePath) {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(inputFilePath);
            FileOutputStream fileOutputStream =
                    new FileOutputStream(outputFilePath);
            GZIPOutputStream gzipOutputStream =
                    new GZIPOutputStream(fileOutputStream);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fileInputStream.read(buffer)) != -1) {
                gzipOutputStream.write(buffer, 0, len);
            }
            gzipOutputStream.close();
            fileOutputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void extractGzipFile(String inputFilePath,
                                       String outputFilePath) {
        try {
            FileInputStream fileInputStream =
                    new FileInputStream(inputFilePath);
            GZIPInputStream gzipInputStream =
                    new GZIPInputStream(fileInputStream);
            FileOutputStream fileOutputStream =
                    new FileOutputStream(outputFilePath);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzipInputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            fileOutputStream.close();
            gzipInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
