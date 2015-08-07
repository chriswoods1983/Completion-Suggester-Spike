package com.laterooms.completionsuggester.core;

import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;

import org.apache.lucene.store.*;
import org.apache.lucene.util.BytesRef;

import java.io.File;
import java.io.IOException;

import java.util.List;


/**
 * Created by cwoods on 07/08/2015.
 */
public class Suggester {

    public List<Lookup.LookupResult> findBy(String text) throws IOException {


        Directory directory = FSDirectory.open(new File("/Users/cwoods/index").toPath());

        AnalyzingInfixSuggester infixSuggester = new AnalyzingInfixSuggester(directory, new StandardAnalyzer(), new StandardAnalyzer(), 2, true, false,true);

        infixSuggester.add(new BytesRef("Manchester"), null,10,null);
        infixSuggester.add(new BytesRef("Manchester City Center"), null,8,null);
        infixSuggester.add(new BytesRef("Mansfield"), null, 4, null);
        infixSuggester.add(new BytesRef("Leeds"), null, 9, null);
        infixSuggester.add(new BytesRef("London"), null,12,null);

        infixSuggester.commit();



        List<Lookup.LookupResult> results = infixSuggester.lookup(text,3,false,true);
        infixSuggester.close();
        directory.close();

        return results;
    }

}
