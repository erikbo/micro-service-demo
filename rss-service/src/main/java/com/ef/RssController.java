package com.ef;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by eboh on 25/01/17.
 */
@RestController
public class RssController {

    @RequestMapping(value = "/fetchfeed", method = RequestMethod.POST)
    public ResponseEntity<List<String>> getFeed(@RequestBody PodcastDto podcastDto) throws Exception{
        SyndFeed feed = new SyndFeedInput().build(new XmlReader(new URL(podcastDto.getUrl())));
        List<String> links = feed.getEntries().stream().map(SyndEntry::getLink).collect(toList());
        return ResponseEntity.ok(links);
    }
}
