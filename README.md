# customer-api

  - Feito para cadastrar clientes de uma empresa
  
### New Features!
  - endpoints linkados com hateoas
  - validação de cpf



### Dependências



* Spring boot 2.4.0
* Java 11
* Spring Web
* Jpa 
* Hibernate validator 6.0.21.Final
* MySQL   
* MySQL Driver
* Maven
* Lombok
Recomendável o uso do Postman para vizualizar os resultados dos endpoints.
### Installation

Para instalar esse repositorio em sua máquina voce pode cloná-lo :

```sh
$ git clone https://github.com/Jeremias-2000/customer-api.git
```

ou baixa-lo :

```sh
$ git pull https://github.com/Jeremias-2000/customer-api.git
```

### Development
Antes de tudo,com o MySQL devidamente instalado em sua máquina,crie um novo database com o nome desejado,neste sentido, abra a api,vá no pacote config e troque o nome clients_api pelo nome do database e coloque a senha do seu MySQL.

Você quer contribuir?É ótimo !

Abaixo irei listar os enpoints disponíveis na api

Tendo já feito o clean da api em sua IDE preferida(eu usei o Intellij Community) copie e cole estes links e cole na url do Postman.

salvar um cliente:
```sh
$ localhost:8080/api/v1/client/save
```

retornar todos os clientes:
```sh
$ localhost:8080/api/v1/client/getAll
```
deletar cliente específico:
```sh
$ localhost:8080/api/v1/client/delete/{id}
```


```
License
$ https://github.com/Jeremias-2000
