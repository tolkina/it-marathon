package instinctools.marathon.it.java;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TextService {
    List<String> getTopOfMostCommonWords(MultipartFile file) throws UnsupportedFileTypeException, IOException;

    Boolean verifyBrackets(MultipartFile file) throws UnsupportedFileTypeException, IOException;
}
