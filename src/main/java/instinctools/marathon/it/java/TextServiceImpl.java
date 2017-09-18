package instinctools.marathon.it.java;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        return true;
    }
}
