# Buscame
<p>
<img src="https://img.shields.io/github/stars/gabrielSantosLima/buscame">
<img src="https://img.shields.io/github/forks/gabrielSantosLima/buscame">
<img src="https://img.shields.io/github/issues/gabrielSantosLima/buscame">
</p>
<p>
  Aplicativo que irá auxiliar na busca por melhores produtos utilizando a análise de fotos com inteligência artificial. 
</p>

## Tópicos
- <a href="#sobre">Sobre</a>
  - <a href="#introdução">Introdução</a>
  - <a href="#autores">Autores</a>
- <a href="#funcionalidades">Objetivos</a>
- <a href="#instalação">Instalação</a>

## Sobre
### Introdução
O BuscaMe é um aplicativo mobile de pesquisa por produtos gerais baseado na busca por imagens utilizando inteligência artificial. O app disponibiliza um inteligente serviço de busca e permite que o usuário mantenha um histórico de busca e uma lista de produtos marcados como favoritos. A utilização desses recursos será através de serviços disponibilizados pelas empresas: **Google**, com a plataforma [Google Cloud](https://console.cloud.google.com/?hl=pt-BR), e [IBM](https://cloud.ibm.com/), com os serviços do IBM Watson. O aplicativo pretende auxiliar na busca de melhores ofertas de uma forma fácil e eficaz, garantindo uma experiência agradável para o usuário.

### Autores
<p>
  <img src="https://github.com/gabrielSantosLima.png" width=20 alt="Gabriel Lima">
  <a href="">Gabriel Lima</a>
</p>
<p>
  <img src="https://github.com/jonasjss.png" width=20 alt="Jonas Santos">
  <a href="">Jonas Santos</a>
</p>
<p>
  <img src="https://github.com/melinnediniz.png" width=20 alt="Melinne Diniz">
  <a href="">Melinne Diniz</a>
</p>
<p>
  <img src="https://github.com/sarahj315.png" width=20 alt="Sarah Júlia">
  <a href="">Sarah Júlia</a>
</p>

## Instalação

**Obs: Em ambiente de desenvolvimento**

- Clonar o projeto `git clone https://github.com/gabrielSantosLima/buscame`
- Executar o servidor da [API](https://github.com/gabrielSantosLima/buscame-api)
- Alterar IP da máquina no arquivo [API de busca](https://github.com/gabrielSantosLima/buscame-api) que irá ser consultada
```kotlin
object ProductSearchFactory {
    private const val BASE_URL = "http://<IP-DA-REDE-ONDE-O-SERVIDOR-DA-API-ESTÁ-EXECUTANDO>:8080/api/search/" // ex: 192.168.12.0
```
- Executar projeto

## Funcionalidades
- Buscar por produtos relacionados ao termo baseados nas fotos tiradas pelo usuário;
- Buscar por produtos relacionados ao termo baseados nas imagens importadas da galeria do aparelho;
- Buscar por produtos via texto;
- Processar as imagens carregadas, indicando as entidades ou objetos presentes na imagem;
- Filtrar buscas por nome do site, marca e/ou preço;
- Favoritar links de produtos encontrados;
- Disponibilizar links de acesso aos produtos;
- Compartilhar links de ofertas de produtos;
- Guardar histórico de acesso aos links.
