package com.laterooms.completionsuggester.cli;

import com.laterooms.completionsuggester.CompletionSuggesterConfiguration;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IndexInput;
import org.apache.lucene.util.BytesRef;

import java.io.File;

/**
 * Created by chris on 07/08/15.
 */
public class IndexCommand extends ConfiguredCommand<CompletionSuggesterConfiguration>

{
    public IndexCommand() {
        super("index", "Created lucene index in configured folder location");
    }

    @Override
    protected void run(Bootstrap<CompletionSuggesterConfiguration> bootstrap, Namespace namespace, CompletionSuggesterConfiguration completionSuggesterConfiguration) throws Exception {

        Directory directory = FSDirectory.open(new File(completionSuggesterConfiguration.getIndexLocation()).toPath());

        Document document1 = new Document();
        Field field1 = new TextField("Text", "Manchester", Field.Store.YES);
        field1.setBoost(10);
        document1.add(field1);

        Document document2 = new Document();
        Field field2 = new TextField("Text", "Mansfield", Field.Store.YES);
        field2.setBoost(8);
        document2.add(field2);

        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());

        IndexWriter indexWriter = new IndexWriter(directory,config);

        indexWriter.deleteAll();
        indexWriter.commit();

        indexWriter.addDocument(document1);
        indexWriter.addDocument(document2);

        indexWriter.close();
        directory.close();

    }

}
