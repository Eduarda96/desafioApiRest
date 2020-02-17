# desafioApiRest
# Executando o aplicativo localmente
Para executar o aplicativo em sua máquina local, você deve clicar com o botão  direito sobre a classe principal  DesafioApiRestAplication e selecionar a opção Run 'DesafioApiRest.main()'
Como alternativa, você pode usar o plugin Spring Boot Maven adicionando no seu arquivo pom.xml o seguinte código:
<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
  
Depois no terminal execute o seguinte comando:
mvn spring-boot: run

# Funcionamento do Sistema:
O sistema contém um banco in-memory, onde ao ser executado o banco já cria uma conta origem com o saldo 2000 reias.
O sistema tem como padrão a porta 8080, para ser apresentada a tela inicial do sistema, digite no navegador http://localhost:8080/api/, ao acessar essa tela o sistema apresentará um botão para que a transferência seja efetuada.
Ao clicar no botão será apresentada a tela onde deve ser inserido os dados da conta destino, conta(6 digitos), agência(4), cpf(11) digitos, nome(somente letras),  com saldo padrão de 1000 reias.
Ao adicionar a conta destino, o sistema apresentará a tela de transferência, o campo aceita números positovos entre 20 até 2000, apresentado uma tela de erro caso o número digitado não esteja entre este intervalo, voltando para o menu principal.
Na tela de transferência se o valor for digitado entre o intervalo, será apresentado a tela com o botão para verificar o lançamento, apresentado os dados finais.

# Implementações futuras:
Criar uma tela de login, onde a conta cadastrada no banco deve inseri seu dados para se logar, conta, agência e senha. Após os dados inserido o sistema apresentaria a tela de menu principal, com os dados pessoais da conta origem, como saldo e movimentações e tipo de banco para o qual será efetuada a transferência. 




