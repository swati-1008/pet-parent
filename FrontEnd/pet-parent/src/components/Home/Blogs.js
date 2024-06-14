import React from "react";
import Carousel from "react-material-ui-carousel";
import * as S from './styles';
import { Grid } from "@mui/material";
import blog1 from '../../assets/images/Home/Blogs/blog1.png';
import blog2 from '../../assets/images/Home/Blogs/blog2.png';
import blog3 from '../../assets/images/Home/Blogs/blog3.png';
import blog4 from '../../assets/images/Home/Blogs/blog4.png';
import blog5 from '../../assets/images/Home/Blogs/blog5.png';
import blog6 from '../../assets/images/Home/Blogs/blog6.png';
import blog7 from '../../assets/images/Home/Blogs/blog7.png';
import blog8 from '../../assets/images/Home/Blogs/blog8.png';

const Blogs = () => {
    const blogs = [
        {
            image: blog1,
            title: 'Understanding Pet Nutrition',
            description: 'Learn about the essential nutrients your pet needs to stay healthy and happy.',
            author: 'Dr. Jane Smith',
        },
        {
            image: blog2,
            title: 'Common Pet Ailments and Remedies',
            description: 'A guide to identifying and treating common health issues in pets.',
            author: 'Dr. John Doe',
        },
        {
            image: blog3,
            title: 'Keeping Your Pet Active',
            description: 'Discover fun and engaging ways to keep your pet physically active.',
            author: 'Dr. Emily Johnson',
        },
        {
            image: blog4,
            title: 'Grooming Tips for Your Pet',
            description: 'Tips and tricks to keep your pet looking and feeling their best.',
            author: 'Dr. Chris Lee',
        },
        {
            image: blog5,
            title: 'Vaccinations and Preventive Care',
            description: 'The importance of vaccinations and preventive care for your pet.',
            author: 'Dr. Alex Brown',
        },
        {
            image: blog6,
            title: 'Understanding Pet Behavior',
            description: 'An overview of common pet behaviors and how to address them.',
            author: 'Dr. Katie Wilson',
        },
        {
            image: blog7,
            title: 'Pet Dental Care',
            description: 'How to maintain your pet\'s dental health and prevent dental diseases.',
            author: 'Dr. Mike Davis',
        },
        {
            image: blog8,
            title: 'First Aid for Pets',
            description: 'Essential first aid tips for handling pet emergencies.',
            author: 'Dr. Laura White',
        },
    ];
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
                                <S.BlogCard>
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

// TODO: Use Web Crawling to fetch blogs real time