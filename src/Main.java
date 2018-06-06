import com.mh.mobile.core.ConvertToSQLFile;
import com.mh.mobile.core.PushToMySQLDatabase;
import com.mh.mobile.core.PushToServer;

/**
 * The first argument is sourceFile which is CSV file</br>
 * The format of the CSV file should follow below rules: sequence, section, province, city, vendor, area code, zip code</br>
 * e.g.
 * <pre>
 * 1	1300000	ɽ��	����	�й���ͨ	531	250000
 * 2	1300001	����	����	�й���ͨ	519	213000
 * 3	1300002	����	����	�й���ͨ	551	238000
 * 4	1300003	�Ĵ�	�˱�	�й���ͨ	831	644000
 * 5	1300004	�Ĵ�	�Թ�	�й���ͨ	813	643000
 * </pre>
 * @author Jason
 *
 */
public class Main {

	public static void main(String[] args) throws Exception {
		String sourceFile = args[0];
		String targetType = args[1];
		if ("sqlfile".equalsIgnoreCase(targetType)) { // ����insert��sql���
			String targetFile = args[2];
			ConvertToSQLFile.genSQLFile(sourceFile, targetFile);
		} else if ("mysql".equalsIgnoreCase(targetType)) { // ֱ�Ӳ��뵽mysql���ݿ�
			// url::username::password
			// jdbc:mysql://localhost:3306/appointment::root::root
			String link = args[2];
			PushToMySQLDatabase.push(sourceFile, link);
		} else if ("restful".equalsIgnoreCase(targetType)) { // ����restful�ӿڴ�������
			String apiurl = args[2];
			PushToServer.push(sourceFile, apiurl);
		} else if ("redis".equalsIgnoreCase(targetType)) { // ֱ�Ӳ��뵽redis���ݿ�
			// TODO
		} else if ("excel".equalsIgnoreCase(targetType)) { // ����excel�������
			// TODO
		} else if ("sort".equalsIgnoreCase(targetType)) { // �������¼
			// TODO
		}
	}

}
