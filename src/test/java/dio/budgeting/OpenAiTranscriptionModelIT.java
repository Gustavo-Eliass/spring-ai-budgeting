package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class OpenAiTranscriptionModelIT {
    @Autowired
    OpenAiAudioTranscriptionModel openAiTranscriptionModel;

    @ParameterizedTest//Basicamente irá colocar o nome do arquivo de audio e a palavraa chave que ele procura
    @CsvSource({
            "padaria_audio.mp3, 40 reais",
            "jantar_conta.mp3, 200 reais",
            "farmacia_audio.mp3, 80 reais",
            "delivery_conta.mp3, 120 reais",
            "cinema_combo.mp3, 90 reais",
    })
    public void should_containExpectedKeywords_when_audioFilesAreProcessed(String fileName, String expectedKeyword) {
        var recording = new ClassPathResource("audio/" + fileName);


        var response = openAiTranscriptionModel.call(recording);


        assertThat(response).isNotEmpty();
        assertThat(response).contains(expectedKeyword);
        System.out.println(response);



    }
}
