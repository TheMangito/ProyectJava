package com.example.ddtech;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.layout.AnchorPane;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public class Tienda{

    private final File articles = new File("src/main/resources/com/example/ddtech/articulosf.json");
    private List<Articulo> articlesList = new ArrayList<>();
    private List<ReusableArticle> articleCreated = new ArrayList<ReusableArticle>();
    public void loadArticles() throws IOException {
        try {
            String json = FileUtils.readFileToString(articles, StandardCharsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            articlesList = objectMapper.readValue(json, new TypeReference<>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Tienda() {
        try {
            loadArticles();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Articulo> selectCategory(String search){
        List<Articulo> articles = new ArrayList<>();
        for (Articulo article: articlesList){
            if(article.buscar(search))
                articles.add(article);
        }
        return articles;
    }
    public void reusableAlert(Articulo article, AnchorPane pane , Double x, Double y){
        ReusableArticle ArticleNode= new ReusableArticle();
        if (article.getImage()!=""){
            ArticleNode.setImage(article.getImage());
        }
        ArticleNode.setTitleText(article.getNombre() + "\n" + article.getDescripcion());
        ArticleNode.setLayoutX(x);
        ArticleNode.setCostLabel(article.getPrecio());
        if(pane.getPrefHeight()<y+750){
            pane.setPrefHeight(y+750);
        }
        ArticleNode.setLayoutY(y);
        articleCreated.add(ArticleNode);
        pane.getChildren().add(ArticleNode);
    }

    public void clearArticles(AnchorPane pane){
        for (ReusableArticle article: articleCreated){
            pane.getChildren().remove(article);
        }
    }
    public List<Articulo> getArticlesList(){
        return articlesList;
    }
}
