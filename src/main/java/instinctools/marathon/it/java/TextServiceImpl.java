package instinctools.marathon.it.java;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service("textService")
public class TextServiceImpl implements TextService {

    private String excludeConjPronPrep(String text) throws FileNotFoundException {
        File prepositionsFile = new File("D:\\Studing\\java\\src\\main\\resources\\prepositions");
        List<String> prepositions = new ArrayList<>();
        Scanner scannerPrepositions = new Scanner(prepositionsFile);
        while (scannerPrepositions.hasNextLine())
            prepositions.add(scannerPrepositions.nextLine());
        for (String preposition : prepositions) {
            String regex = String.format("(?i)[^А-я]%s[^А-я]", preposition);
            text = text.replaceAll(regex, " ");
        }

        File conjunctionsFile = new File("D:\\Studing\\java\\src\\main\\resources\\conjunctions");
        List<String> conjunctions = new ArrayList<>();
        Scanner scannerConjuctions = new Scanner(conjunctionsFile);
        while (scannerConjuctions.hasNextLine())
            conjunctions.add(scannerConjuctions.nextLine());
        for (String conjunction : conjunctions) {
            String regex = String.format("(?i)[^А-я]%s[^А-я]", conjunction);
            text = text.replaceAll(regex, " ");
        }

        File pronounsFile = new File("D:\\Studing\\java\\src\\main\\resources\\pronouns");
        List<String> pronouns = new ArrayList<>();
        Scanner scannerPronouns = new Scanner(pronounsFile);
        while (scannerPronouns.hasNextLine())
            pronouns.add(scannerPronouns.nextLine());
        for (String pronoun : pronouns) {
            String regex = String.format("(?i)[^А-я]%s[^А-я]", pronoun);
            text = text.replaceAll(regex, " ");
        }
        return text;
    }

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
        text = excludeConjPronPrep(text.toLowerCase());

        Pattern wordPattern = Pattern.compile("[А-я]+-?[А-я]+");
        List<String> tokens = new ArrayList<>();
        Matcher matcher = wordPattern.matcher(text);
        while (matcher.find()) {
            String word = matcher.group();
            tokens.add(word);
        }
        Map<String, Long> map = tokens.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

        tokens = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return tokens;
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
