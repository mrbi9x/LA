package net.luvina.manageuser.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.luvina.manageuser.entities.UserInfor;
import net.luvina.manageuser.logics.impl.TblUserLogicImpl;
import net.luvina.manageuser.utils.Common;
import net.luvina.manageuser.utils.ConfigProperties;
import net.luvina.manageuser.utils.MessageProperties;

/**
 * Class xử lý export UserInfor theo trình độ tiếng nhật ra file CSV
 * @author Nguyễn Văn Minh
 * Servlet implementation class ExportCSVController
 */
public class ExportCSVController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DELIMITER = ",";
	private static final String NEW_LINE = "\n";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportCSVController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] japanCodeLevel = request.getParameterValues("japanLevel");
		String getAllJapanLevel = request.getParameter("ALL");
		List<UserInfor> listUserInfor = new ArrayList<UserInfor>();
		TblUserLogicImpl userLogicImpl = new TblUserLogicImpl();
		boolean isGetAllUserInfor = false;
		if (getAllJapanLevel != null && "ALL".equals(getAllJapanLevel)) {
			isGetAllUserInfor = true;
		}
		listUserInfor = userLogicImpl.getListUserByCodeLevel(isGetAllUserInfor, japanCodeLevel);
		String fileName = "UserInformationReportByJapanLevel" + "_" + System.currentTimeMillis() + ".csv";
		StringBuilder headerCsv = getHeaderCsvByJapanLevel();
		StringBuilder contentCsv = getContentCsvFromUserInfor(listUserInfor);
		Common.exportCsv(fileName, headerCsv, contentCsv, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * getContentCsvFromUserInfor - Hàm lấy về nội dung của file CSV theo List UserInfor
	 * @param listUserInfor - Danh sách user infor theo trình độ tiếng nhật
	 * @return contentCsv - Nội dung file CSV
	 */
	private StringBuilder getContentCsvFromUserInfor(List<UserInfor> listUserInfor) {
		StringBuilder contentCsv = new StringBuilder();
		if (listUserInfor.size() > 0) {
			for (UserInfor userInfor : listUserInfor) {
				contentCsv.append(userInfor.getUserId());
				contentCsv.append(DELIMITER);
				contentCsv.append(userInfor.getFullName());
				contentCsv.append(DELIMITER);
				contentCsv.append(Common.dateToStringFormat(userInfor.getBirthday()));
				contentCsv.append(DELIMITER);
				contentCsv.append(userInfor.getGroupName());
				contentCsv.append(DELIMITER);
				contentCsv.append(userInfor.getEmail());
				contentCsv.append(DELIMITER);
				contentCsv.append(userInfor.getTel());
				contentCsv.append(DELIMITER);
				String nameLevel = userInfor.getNameLevel();
				if (nameLevel != null && !"".equals(nameLevel)) {
					contentCsv.append(nameLevel);
					contentCsv.append(DELIMITER);
					contentCsv.append(Common.dateToStringFormat(userInfor.getEndDate()));
					contentCsv.append(DELIMITER);
					contentCsv.append(userInfor.getTotal());
					contentCsv.append(DELIMITER);
				}
				contentCsv.append(NEW_LINE);
			}
		} else {
			contentCsv.append(MessageProperties.getMessage("MSG005"));
		}

		return contentCsv;
	}

	/**
	 * getHeaderCsvByJapanLevel - Hàm lấy về header của file CSV từ file Config
	 * @return headerCsv - Chuỗi header của file CSV
	 */
	private StringBuilder getHeaderCsvByJapanLevel() {
		StringBuilder headerCsv = new StringBuilder();
		headerCsv.append(ConfigProperties.getMessage("headerCsvByJapanLevel"));
		headerCsv.append(NEW_LINE);
		return headerCsv;
	}
}
