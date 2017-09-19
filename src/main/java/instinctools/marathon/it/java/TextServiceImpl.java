package instinctools.marathon.it.java;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


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

    private boolean isOpenBracket(Character character) {
        return character.equals('(') || character.equals('{') || character.equals('[');
    }

    private boolean isCloseBracket(Character character) {
        return character.equals(')') || character.equals('}') || character.equals(']');
    }

    @Override
    public boolean verifyBrackets(MultipartFile file) throws UnsupportedFileTypeException, IOException {
        verifyFileExtension(file);
        String text = new String(file.getBytes());
        Stack<Character> brackets = new Stack<>();
        Character character;
        for (int i = 0; i < text.length(); i++) {
            character = text.charAt(i);
            if (isOpenBracket(character)) {
                brackets.push(character);
            } else if (isCloseBracket(character)) {
                if (brackets.empty()) {
                    return false;
                } else if ((character.equals(')') && brackets.peek().equals('(')) ||
                        (character.equals('}') && brackets.peek().equals('{')) ||
                        (character.equals(']') && brackets.peek().equals('['))) {
                    brackets.pop();
                }
            }
        }
        return brackets.empty();
    }
}
