package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Download extends HttpServlet {
	public static final int bufferSize = 10240; // 10ko

	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		String path = this.getServletConfig().getInitParameter("path");
		String requestedFile = request.getPathInfo();
		if (requestedFile == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		requestedFile = URLDecoder.decode(requestedFile, "UTF-8");
		File file = new File(path, requestedFile);
		if (!file.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		String type = getServletContext().getMimeType(file.getName());
		if (type == null) {
			type = "application/octet-stream";
		}

		response.reset();
		response.setBufferSize(bufferSize);
		response.setContentType(type);
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;
		try {
			input = new BufferedInputStream(new FileInputStream(file), bufferSize);
			output = new BufferedOutputStream(response.getOutputStream(), bufferSize);

			byte[] buffer = new byte[bufferSize];
			int longueur;
			while ((longueur = input.read(buffer)) > 0) {
				output.write(buffer, 0, longueur);
			}
		} finally {
			output.close();
			input.close();
		}
	}
}
