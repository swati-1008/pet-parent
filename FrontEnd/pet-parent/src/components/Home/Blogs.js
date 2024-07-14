import React, { useEffect, useState } from "react";
import Carousel from "react-material-ui-carousel";
import * as S from './styles';
import { Grid } from "@mui/material";
import fetchBlogFeed from "./blogFeed";

const Blogs = () => {
    const [blogs, setBlogs] = useState([]);

    useEffect(() => {
        const fetchBlogs = async () => {
            const url = 'https://www.pethealthnetwork.com/rss.xml';
            const fetchedBlogs = await fetchBlogFeed(url);
            setBlogs(fetchedBlogs.slice(0, 8));
        }
        fetchBlogs();
    }, []);
    const chunkArray = (arr, size) => {
        const result = [];
        for (let i = 0; i < arr.length; i+= size) {
            result.push(arr.slice(i, i + size));
        }
        return result;
    }
    const groupedBlogs = chunkArray(blogs, 3);
    return (
        <S.BlogContainer style = {{ backgroundColor: '#FFF' }}>
            <S.Headline variant = 'h4' component = 'h2' align = 'center' gutterBottom>
                Fur-ever Healthy Tips
            </S.Headline>
            <Carousel
                autoPlay
                animation = 'slide'
                indicators
                navButtonsAlwaysVisible
                cycleNavigation
                interval = { 4000 }
                timeout = { 1000 }
            >
                { groupedBlogs.map((group, index) => (
                    <Grid container spacing = { 2 } key = { index }>
                        { group.map((blog, idx) => (
                            <Grid item xs = { 12 } md = { 4 } key = { idx }>
                                <S.BlogCard onClick = { () => window.open(blog.link, '_blank') }>
                                    <S.BlogImage src = { blog.image } alt = { blog.title } />
                                    <S.BlogTitle variant = 'h6'>{ blog.title }</S.BlogTitle>
                                    <S.BlogDescription variant = 'body2'>{ blog.description }</S.BlogDescription>
                                    <S.BlogAuthor variant = 'body2'>By { blog.author }</S.BlogAuthor>
                                </S.BlogCard>
                            </Grid>
                        )) }
                    </Grid>
                )) }
            </Carousel>
        </S.BlogContainer>
    );
};

export default Blogs;