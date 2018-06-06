import com.mh.mobile.core.ConvertToSQLFile;
import com.mh.mobile.core.PushToMySQLDatabase;
import com.mh.mobile.core.PushToServer;

/**
 * The first argument is sourceFile which is CSV file</br>
 * The format of the CSV file should follow below rules: sequence, section, province, city, vendor, area code, zip code</br>
 * e.g.
 * <pre>
 * 1	1300000	山东	济南	中国联通	531	250000
 * 2	1300001	江苏	常州	中国联通	519	213000
 * 3	1300002	安徽	巢湖	中国联通	551	238000
 * 4	1300003	四川	宜宾	中国联通	831	644000
 * 5	1300004	四川	自贡	中国联通	813	643000
 * </pre>
 * @author Jason
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		String sourceFile = args[0];
		String targetType = args[1];
		if ("sqlfile".equalsIgnoreCase(targetType)) { // 生成insert的sql语句
			String targetFile = args[2];
			ConvertToSQLFile.genSQLFile(sourceFile, targetFile);
		} else if ("mysql".equalsIgnoreCase(targetType)) { // 直接插入到mysql数据库
			// url::username::password
			// jdbc:mysql://localhost:3306/appointment::root::root
			String link = args[2];
			PushToMySQLDatabase.push(sourceFile, link);
		} else if ("restful".equalsIgnoreCase(targetType)) { // 调用restful接口传输数据
			String apiurl = args[2];
			PushToServer.push(sourceFile, apiurl);
		} else if ("redis".equalsIgnoreCase(targetType)) { // 直接插入到redis数据库
			// TODO
		} else if ("excel".equalsIgnoreCase(targetType)) { // 生成excel表格数据
			// TODO
		} else if ("sort".equalsIgnoreCase(targetType)) { // 重排序记录
			// TODO
		}
	}

}
