package instinctools.marathon.it.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TextController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextController.class);
    @Autowired
    TextService textService;

    /**
     * Get top of 10 most common words
     *
     * @param file txt-file with text for analysis
     * @return the ResponseEntity with status
     * 200 (OK) and with body containing top of 10 most common words,
     * or with status 500 (Internal Server Error),
     * or with status 400 (Bad Request).
     */
    @PostMapping("/repeated-words/")
    public ResponseEntity<?> getTopOfMostCommonWords(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Counting 10 the most repetitive words in the text.");
        try {
            List<String> words = textService.getTopOfMostCommonWords(file);
            return new ResponseEntity<>(new Response(words, null, null), HttpStatus.OK);
        } catch (UnsupportedFileTypeException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new Response(null, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new Response(null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Verify that the brackets are correctly positioned.
     *
     * @param file txt-file with text for analysis
     * @return the ResponseEntity with status
     * 200 (OK) and with body containing answer "correct/incorrect",
     * or with status 500 (Internal Server Error),
     * or with status 400 (Bad Request).
     */
    @PostMapping("/brackets/")
    public ResponseEntity<?> verifyBrackets(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Verify that the brackets are correctly positioned.");
        try {
            boolean verifyBrackets = textService.verifyBrackets(file);
            return new ResponseEntity<>(new Response(null, verifyBrackets ? "correct" : "incorrect", null), HttpStatus.OK);
        } catch (UnsupportedFileTypeException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new Response(null, null, e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
            return new ResponseEntity<>(new Response(null, null, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
