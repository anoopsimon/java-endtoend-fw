package core.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CsvUtil {

    public static void main(String[] args) throws Exception {

        String path="C:\\Users\\s4514\\IdeaProjects\\java-endtoend-fw\\core\\src\\test\\resources\\TestData_FF_csv.csv";
        List<Map<String, String>> x=read(new File(path));
        for (Map<String, String> y:x             ) {
            y.keySet().forEach(xx->{
                System.out.println(xx + ":" + y.get(xx) );
            });

            System.out.println("-------------------------");
        }
    }

    /**
     * read a csv file into list of hashmap
     * @param file
     * @return
     * @throws Exception
     */
    public static List<Map<String, String>> read(File file) throws Exception {
        List<Map<String, String>> response = new ArrayList<>();
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        MappingIterator<Map<String, String>> iterator = mapper.reader(Map.class)
                .with(schema)
                .readValues(file);
        while (iterator.hasNext()) {
            response.add(iterator.next());
        }
        return response;
    }
}
