package com.laterooms.completionsuggester.core;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.suggest.DocumentDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;

import org.apache.lucene.search.suggest.analyzing.FuzzySuggester;
import org.apache.lucene.store.*;

import java.io.File;
import java.io.IOException;

import java.util.List;


/**
 * Created by cwoods on 07/08/2015.
 */
public class Suggester {

    public List<Lookup.LookupResult> findBy(String text) throws IOException {


        Directory directory = FSDirectory.open(new File("/tmp/").toPath());

        IndexReader indexReader = DirectoryReader.open(directory);
        Dictionary dictionary = new DocumentDictionary(indexReader, "Text","Weight");

        FuzzySuggester fuzzySuggester = new FuzzySuggester(new StandardAnalyzer());

        fuzzySuggester.build(dictionary);

        List<Lookup.LookupResult> results = fuzzySuggester.lookup(text,null,false,5);

        directory.close();

        return results;
    }

}
