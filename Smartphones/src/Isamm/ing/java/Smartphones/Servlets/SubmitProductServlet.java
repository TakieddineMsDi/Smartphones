package Isamm.ing.java.Smartphones.Servlets;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import Isamm.ing.java.Smartphones.Classes.DBAccessControl;
import Isamm.ing.java.Smartphones.DAO.ProduitsDAO;
import Isamm.ing.java.Smartphones.Models.Clients;
import Isamm.ing.java.Smartphones.Models.Produits;
import Isamm.ing.java.Smartphones.doAll.Do;

public class SubmitProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private long sizeInBytes;
	private String fieldName;
	private boolean isInMemory;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/SubmitProduct.jsp")
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		File file;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		ServletContext context = req.getServletContext();
		String filePath = context.getInitParameter("file-upload");

		// Verify the content type
		String contentType = req.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {

			DiskFileItemFactory factory = new DiskFileItemFactory();
			// maximum size that will be stored in memory
			factory.setSizeThreshold(maxMemSize);
			// Location to save data that is larger than maxMemSize.
			factory.setRepository(new File(context
					.getInitParameter("temp-upload")));

			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// maximum file size to be uploaded.
			upload.setSizeMax(maxFileSize);
			try {
				// Parse the request to get file items.
				List<?> fileItems = upload.parseRequest(req);
				// Process the uploaded file items
				Iterator<?> i = fileItems.iterator();
				int Marque = 0;
				String Libelle = null;
				Double PrixUnitaire = (double) 0;
				int Quantite = 0;
				String Description = null;
				int ID = ((Clients) req.getSession().getAttribute(
						"SmartphonesUserID")).getID();
				int IDProd = DBAccessControl.getNewID("produits");
				String fileName = null;
				while (i.hasNext()) {

					FileItem fi = (FileItem) i.next();
					if (fi.isFormField()) {
						if (fi.getFieldName().equals("Marque"))
							Marque = Integer.parseInt(fi.getString());
						if (fi.getFieldName().equals("Libelle"))
							Libelle = fi.getString();
						if (fi.getFieldName().equals("PrixUnitaire"))
							PrixUnitaire = Double.parseDouble(fi.getString());
						if (fi.getFieldName().equals("Quantite"))
							Quantite = Integer.parseInt(fi.getString());
						if (fi.getFieldName().equals("Description"))
							Description = fi.getString();

					}
					if (!fi.isFormField()) {
						// Get the uploaded file parameters
						setFieldName(fi.getFieldName());
						fileName = fi.getName();
						setInMemory(fi.isInMemory());
						setSizeInBytes(fi.getSize());
						// Write the file
						fileName = Libelle
								+ "_"
								+ IDProd
								+ "_"
								+ Marque
								+ "_"
								+ ID
								+ fileName.substring(fileName
										.lastIndexOf("."));
						file = new File(filePath + fileName);
						fi.write(file);
						resp.getWriter().println(
								"<script type=\"text/javascript\">"
										+ "alert(\"" + fileName
										+ "Uploaded\");" + "</script>");

					}
				}
				Date dNow = new Date();
				java.sql.Date sqldate = new java.sql.Date(dNow.getTime());
				ProduitsDAO.add("this",new Produits(IDProd, Libelle,
						PrixUnitaire, Quantite, fileName, Marque, Description,
						2, ID, 1, sqldate,0,0));
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else {
			resp.getWriter().println(
					"<script type=\"text/javascript\">"
							+ "alert(\"erreur! no file Uploaded\");"
							+ "</script>");
		}
	}

	public long getSizeInBytes() {
		return sizeInBytes;
	}

	public void setSizeInBytes(long sizeInBytes) {
		this.sizeInBytes = sizeInBytes;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isInMemory() {
		return isInMemory;
	}

	public void setInMemory(boolean isInMemory) {
		this.isInMemory = isInMemory;
	}

}
