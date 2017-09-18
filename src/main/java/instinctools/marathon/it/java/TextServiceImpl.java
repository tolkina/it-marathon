package instinctools.marathon.it.java;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service("textService")
public class TextServiceImpl implements TextService {

    private void verifyFileExtension(MultipartFile file) throws UnsupportedFileTypeException {
        String filename = file.getOriginalFilename();
        String fileExtension = FilenameUtils.getExtension(filename);
        if (!fileExtension.toLowerCase().equals("txt")) throw new UnsupportedFileTypeException(
                String.format("File extension %s not supported", fileExtension));
    }

    @Override
    public List<String> getTopOfMostCommonWords(MultipartFile file) throws UnsupportedFileTypeException, IOException {
        verifyFileExtension(file);
        String text = new String(file.getBytes());
        return null;
    }

    @Override
    public Boolean verifyBrackets(MultipartFile file) throws UnsupportedFileTypeException, IOException {
        verifyFileExtension(file);
        String text = new String(file.getBytes());
        List<Character> brackets = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '(' || text.charAt(i) == '{' || text.charAt(i) == '[' ||
                    text.charAt(i) == ')' || text.charAt(i) == '}' || text.charAt(i) == ']') {
                brackets.add(text.charAt(i));
            }
        }
        for (int i = 0; i < brackets.size() - 1; i++) {
            if ((brackets.get(i) == '(' && brackets.get(i + 1) == ')') ||
                    (brackets.get(i) == '[' && brackets.get(i + 1) == ']') ||
                    (brackets.get(i) == '{' && brackets.get(i + 1) == '}')) {
                brackets.remove(i);
                brackets.remove(i);
                i = -1;
            }
        }
        return brackets.isEmpty();
    }
}
