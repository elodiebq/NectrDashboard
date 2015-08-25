package controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import javax.imageio.ImageIO;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import model.BeaconDAO;
import model.BeaconDAO2;
import model.BusinessProfileDAO;
import model.Model;
import model.MyDAOException;
import model.RegionDAO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.mybeans.form.FileProperty;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;




import com.mysql.jdbc.Util;

import databeans.BeaconBean;
import databeans.BusinessProfileBean;
import databeans.RegionBean;
import formbeans.CreateBusinessProfileForm;

public class CreateBusinessAction extends Action {
    private FormBeanFactory<CreateBusinessProfileForm> formBeanFactory = FormBeanFactory
            .getInstance(CreateBusinessProfileForm.class);

    private String jdbcDriver  = "com.mysql.jdbc.Driver";
  //  private String jdbcURL = "jdbc:mysql:///test";
    private String jdbcURL = "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789";

    private String tableName;
    private BusinessProfileDAO customerDAO;
    private RegionDAO regionDAO;
    private BeaconDAO2 beaconDAO;
    
    public static final boolean _DEBUG_ = true;
	private static final int HOST_CLASS_INDEX = 3;

    public CreateBusinessAction(Model model) {
        customerDAO = model.getBusinessProfileDAO();
        regionDAO = model.getRegionDAO();
    }

    public String getName() {
        return "create_business.do";
    }

    public String perform(HttpServletRequest request) {
        // BusinessProfileBean business = (BusinessProfileBean)
        // request.getSession(false).getAttribute("business");
        // if (employ == null) {
        // return "login.jsp";
        // }
        try {

            List<String> errors = new ArrayList<String>();
            request.setAttribute("errors", errors);

            CreateBusinessProfileForm form = (CreateBusinessProfileForm) formBeanFactory
                    .create(request);

            if (!form.isPresent())
                return "create_business.jsp";

            errors.addAll(form.getValidationErrors());
            BusinessProfileBean[] businessList;
        
            try {
                System.out.print("I'm here");
                businessList = customerDAO.getBusinessList();
                HashSet<String> list = new HashSet<String>();
                RegionBean[] regions = regionDAO.getRegionList();
                if (regions.length != 0) {

                    for (int i = 0; i < regions.length; i++) {
                        list.add(regions[i].getRegionName());

                    }
                    String[] region = new String[regions.length];
                    java.util.Iterator<String> iterator = list.iterator();
                    int i = 0;
                    while (iterator.hasNext()) {
                        region[i] = iterator.next();
                        i++;
                    }

                    for (int j = 0; j < region.length; j++) {
                        System.out.println(region[j]);
                    }
                    
                    request.setAttribute("region", region);
                }
            } catch (RollbackException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            BusinessProfileBean customer = new BusinessProfileBean();
            BeaconBean beacon = new BeaconBean();
            RegionBean region1 = new RegionBean();
            if (form.getRegion() != null){
            try {
                region1 = regionDAO.getRegion(form.getRegion());
                System.out.print(region1.getRegionName());
            } catch (RollbackException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            customer.setRegionId(region1.getRegionId());
            }
            try {
				beaconDAO = new BeaconDAO2("com.mysql.jdbc.Driver", "jdbc:mysql://aatlnydnhg5jd9.cw0kvjz4dk33.us-east-1.rds.amazonaws.com:3306/ebdb?user=nectr&password=123456789", "beacon");
			} catch (MyDAOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

            customer.setName(form.getName());
         
            customer.setPhone(form.getPhone());
            customer.setDescription(form.getDescription());
            customer.setOperation_housrs(form.getOperation_hours());
            customer.setWebsite(form.getWebsite());
            customer.setUsername(form.getUsername());
            customer.setPassword(form.getPassword());
            customer.setAddress(form.getAddress());
            customer.setCity(form.getCity());
            customer.setInLat(form.getInLat());
            customer.setInLng(form.getInLnt());
            customer.setCategory(form.getCategory());
            customer.setBeaconId(beaconDAO.getLastId());
            System.out.println(beaconDAO.getLastId());

            FileProperty fileProp = form.getImage();
            String webContentPath = request.getServletContext()
					.getRealPath("/");
			String fileLocalName = form.getName() + form.getPhone() + ".jpg";
			String pathInWebContent = CreateBusinessAction.getPath("images/upload", fileLocalName);
			String pathInSystem = CreateBusinessAction.getPath(webContentPath, pathInWebContent);
			System.out.println("save file to " + pathInSystem);
			String monday = "" + form.getMonday_from() + "to" + form.getMonday_to();
			customer.setMonday(monday);
			String tuesday = "" + form.getTuesday_from() + "to" + form.getTuesday_to();
			customer.setTuesday(tuesday);
			String wednesday = "" + form.getWednesday_from() + "to" + form.getWednesday_to();
			customer.setWednesday(wednesday);
			String thursday = "" + form.getThursday_from() + "to" + form.getThursday_to();
			customer.setThursday(thursday);
			String friday = "" + form.getFriday_from() + "to" + form.getFriday_to();
			customer.setFriday(friday);
			String saturday = "" + form.getSaturday_from() + "to" + form.getSaturday_to();
			customer.setSaturday(saturday);
			String sunday = "" + form.getSunday_from() + "to" + form.getSunday_to();
			customer.setSunday(sunday);
			
			
			
			try {
				FileUtils.writeByteArrayToFile(new File(pathInSystem),fileProp.getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// save to db
			customer.setImage(pathInWebContent);

            
            try {
                customerDAO.create(customer);
                request.setAttribute("msg",
                        "Business account for " + customer.getName() + " "
                                + " has been created");
            } catch (RollbackException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
	
        } catch (FormBeanException e) {
            // // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MyDAOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        return "create_business.jsp";
    }
	
	public static void e(Object... msg) {
		if (_DEBUG_) {
			String logMsg = CreateBusinessAction.getString(msg);
		}
	}

	public static String bytesToHex(byte[] bytes) {
		if (bytes == null) {
			return "";
		}
		StringBuffer digestSB = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			int lowNibble = bytes[i] & 0x0f;
			int highNibble = (bytes[i] >> 4) & 0x0f;
			digestSB.append(Integer.toHexString(highNibble));
			digestSB.append(Integer.toHexString(lowNibble));
		}
		return digestSB.toString();
	}

	public static byte[] hexToBytes(String hexString) {
		byte[] b = new byte[hexString.length() / 2];
		for (int i = 0; i < b.length; i++) {
			int index = i * 2;
			int v = Integer.parseInt(hexString.substring(index, index + 2), 16);
			b[i] = (byte) v;
		}
		return b;
	}

	public static String getClassNameByStackIndex(int index) {
		try {
			String name = Thread.currentThread().getStackTrace()[index]
					.getClassName();
			int dot = name.lastIndexOf('.');
			return name.substring(dot + 1);
		} catch (Exception e) {
		}
		return "";
	}

	public static String getHostFunctionName(int index) {
		try {
			return Thread.currentThread().getStackTrace()[index]
					.getMethodName();
		} catch (Exception e) {
		}
		return "unknown method";
	}

	public static String toString(Object object) {
		StringBuilder sb = new StringBuilder();
		Class<?> cls = object.getClass();
		sb.append(cls.getSimpleName());
		sb.append("{");
		boolean isFirstItem = true;
		while (cls != Object.class) {
			Field[] f = cls.getDeclaredFields();
			for (Field field : f) {
				if (Modifier.isStatic(field.getModifiers())) {
					continue;
				}
				if (!field.isAccessible()) {
					field.setAccessible(true);
				}
				String fieldName = field.getName();
				Object fieldValue = null;
				try {
					fieldValue = field.get(object);
				} catch (Exception e) {
					continue;
				}
				if (!isFirstItem) {
					sb.append(", ");
				}
				sb.append(fieldName);
				sb.append(" = ");
				sb.append(fieldValue);
				isFirstItem = false;
			}
			cls = cls.getSuperclass();
		}
		sb.append("}");
		return sb.toString();
	}

	public static String getString(Object... objects) {
		if (objects == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Object o : objects) {
			if (o != null) {
				sb.append(o.toString());
			}
		}
		return sb.toString();
	}

	public static byte[] getUtf8Bytes(String s) {
		if (s == null) {
			return null;
		}
		try {
			return s.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return s.getBytes();
	}

	public static String getUtf8String(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		try {
			return new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	public static String getSHA1(String content, Integer salt) {
		byte[] md5 = digest("SHA1", content.getBytes(), salt);
		return bytesToHex(md5);
	}

	public static String getSHA1(byte[] content, Integer salt) {
		byte[] md5 = digest("SHA1", content, salt);
		return bytesToHex(md5);
	}

	public static byte[] digest(String algorithm, byte[] content, Integer salt) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {
			throw new AssertionError(
					"Can't find the SHA1 algorithm in the java.security package");
		}

		if (salt != null) {
			String saltString = String.valueOf(salt);
			md.update(saltString.getBytes());
		}
		md.update(content);
		return md.digest();
	}

	public static byte[] getBytes(InputStream inputStream) {
		if (inputStream == null) {
			return null;
		}
		byte[] result = null;
		try {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = inputStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			result = outStream.toByteArray();
		} catch (Exception e) {
		}
		return result;
	}

	public static void setBytes(OutputStream outputStream, byte[] bytes) {
		if (outputStream == null || bytes == null) {
			return;
		}
		try {
			outputStream.write(bytes);
			outputStream.flush();
		} catch (IOException e) {
		} finally {
			try {
				outputStream.close();
			} catch (IOException e) {
			}
		}
		return;
	}

	public static String formatNumber(double value, String format) {
		DecimalFormat df = new java.text.DecimalFormat(format);
		return df.format(value);
	}

	public static double truncate(double value, int scale) {
		return (double) ((long) (value * scale)) / scale;
	}

	public static boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}

	public static String getPath(Object... file) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < file.length; i++) {
			if (file[i] == null) {
				continue;
			}
			String fileName = file[i].toString();
			sb.append(fileName);
			if (i < file.length -1 && !fileName.endsWith(File.separator)) {
				sb.append(File.separator);
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
	}
	
}