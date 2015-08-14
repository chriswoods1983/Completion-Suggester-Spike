package com.laterooms.completionsuggester.cli;

import com.laterooms.completionsuggester.CompletionSuggesterConfiguration;
import io.dropwizard.cli.ConfiguredCommand;
import io.dropwizard.setup.Bootstrap;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
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
        Field field1w = new LongField("Weight", 10L, Field.Store.YES);
        Field field1p = new TextField("Payload", "k16296355_manchester-hotels.aspx", Field.Store.YES);
        document1.add(field1);
        document1.add(field1w);
        document1.add(field1p);

        Document document2 = new Document();
        Field field2 = new TextField("Text", "Mansfield", Field.Store.YES);
        Field field2w = new LongField("Weight", 6L, Field.Store.YES);
        document2.add(field2);
        document2.add(field2w);

        Document document3 = new Document();
        Field field3 = new TextField("Text", "Manston", Field.Store.YES);
        Field field3w = new LongField("Weight", 8L, Field.Store.YES);
        document3.add(field3);
        document3.add(field3w);

        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());

        IndexWriter indexWriter = new IndexWriter(directory,config);

        indexWriter.deleteAll();
        indexWriter.commit();

        indexWriter.addDocument(document1);
        indexWriter.addDocument(document2);
        indexWriter.addDocument(document3);

        indexWriter.close();
        directory.close();

    }

}
