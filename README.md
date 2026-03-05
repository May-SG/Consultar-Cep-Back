# 📍 Consultar CEP - API Back-end

Esta é uma API desenvolvida em **Java** com o objetivo de realizar a consulta de endereços de forma automatizada, integrando-se com o serviço externo da **ViaCEP**. 
O projeto demonstra habilidades em consumo de APIs REST, manipulação de JSON e estruturação de serviços back-end.

---

### 🚀 Funcionalidades

- [x] Consulta de endereço completo a partir de um CEP válido.
- [x] Integração em tempo real com a API ViaCEP.
- [x] Tratamento de erros para CEPs inexistentes ou formatos inválidos.
- [x] Retorno de dados estruturados em JSON.

---

### 🛠️ Tecnologias Utilizadas

As seguintes ferramentas e linguagens foram utilizadas na construção do projeto:

- **Linguagem:** [Java 11+](https://www.oracle.com/java/ )
- **Framework (opcional):** [Spring Boot](https://spring.io/projects/spring-boot ) (ajuste se for Java puro)
- **Biblioteca HTTP:** [HttpClient](https://openjdk.org/groups/net/httpclient/intro.html ) / [RestTemplate](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html )
- **Gerenciador de Dependências:** [Maven](https://maven.apache.org/ ) / [Gradle](https://gradle.org/ )
- **Integração:** [ViaCEP API](https://viacep.com.br/ )

---

### 📥 Como Executar o Projeto

Para rodar este projeto localmente, você precisará ter o **JDK 11** ou superior instalado.

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/May-SG/Consultar-Cep-Back.git
   ```

2. **Entre na pasta do projeto:**
   ```bash
   cd Consultar-Cep-Back
   ```

3. **Compile e execute:**
   ```bash
   # Se estiver usando Maven:
    mvn clean install
    mvn spring-boot:run
   ```

📑 Exemplo de Uso
Ao realizar uma requisição GET para o endpoint de consulta:
Input (Exemplo ): 01001000
**Output (JSON):**
```json
{
  "cep": "01001-000",
  "logradouro": "Praça da Sé",
  "bairro": "Sé",
  "localidade": "São Paulo",
  "uf": "SP"
}
```

🤝 Contribuições
Sinta-se à vontade para abrir uma Issue ou enviar um Pull Request com melhorias, como:
Adição de cache para consultas repetidas.
Interface front-end em Angular para consumo desta API.
Testes unitários com JUnit.

✉️ Contato
Desenvolvido por Mayara Silva – **LinkedIn:** [in/Mey-Silva](www.linkedin.com/in/mey-silva) – **GitHub**  [May-SG]((https://github.com/May-SG))
  
