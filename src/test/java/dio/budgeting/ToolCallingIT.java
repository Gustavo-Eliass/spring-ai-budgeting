package dio.budgeting;

import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class ToolCallingIT {
    @Autowired
    OpenAiChatModel openAiChatModel;


    static class MathTolls{
        @Tool(description = "Soma dois numeros interios a e b"  )
        public int sum(int a, int b){
            return a + b;
        }

        @Tool(description = "Subtrai dois numeros interios a e b"  )
        public int diff(int a, int b){
            return a - b;
        }
    }

    @Test
    void should_executeSum_when_prompted() {
        var chatClient = ChatClient.builder(openAiChatModel)
                .defaultSystem("Você é um matemático. Use as ferramentas disponíveis para calcular. Responda apenas com o resultado numérico.")
                .defaultTools(new MathTolls())
                .build();

        var response = chatClient.prompt("Soma 10 mais 20, depois subtraia 30")
                .call()
                .content();

        assertThat(response).contains("0");
        System.out.println(response);
    }


}
