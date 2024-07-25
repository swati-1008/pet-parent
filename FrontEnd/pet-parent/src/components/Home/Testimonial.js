import { Grid, Rating } from '@mui/material';
import person1 from '../../assets/images/Home/HomeTestimonial/testimonial1.jpeg';
import person2 from '../../assets/images/Home/HomeTestimonial/testimonial2.jpeg';
import person3 from '../../assets/images/Home/HomeTestimonial/testimonial3.jpeg';
import person4 from '../../assets/images/Home/HomeTestimonial/testimonial4.jpeg';
import person5 from '../../assets/images/Home/HomeTestimonial/testimonial5.jpeg';
import person6 from '../../assets/images/Home/HomeTestimonial/testimonial6.jpeg';
import person7 from '../../assets/images/Home/HomeTestimonial/testimonial7.jpeg';
import person8 from '../../assets/images/Home/HomeTestimonial/testimonial8.jpeg';
import * as S from './styles';
import Carousel from 'react-material-ui-carousel';

const Testimonial = () => {
    const testimonials = [
        {
            image: person1,
            quote: "Purrs N' Paws has completely transformed my pet's life. The services are top-notch!",
            name: 'John Doe',
            rating: 5,
        },
        {
            image: person2,
            quote: "I love how easy it is to connect with other pet parents. The community is amazing!",
            name: 'Jane Smith',
            rating: 4,
        },
        {
            image: person3,
            quote: "The vet consultations have been a lifesaver. I can get professional advice anytime!",
            name: 'Emily Johnson',
            rating: 5,
        },
        {
            image: person4,
            quote: "The events organized by Purrs N' Paws are so much fun. My pet loves them!",
            name: 'Chris Lee',
            rating: 4,
        },
        {
            image: person5,
            quote: "Shopping for my pet has never been easier. They have everything!",
            name: 'Alex Brown',
            rating: 5,
        },
        {
            image: person6,
            quote: "The blog articles are so informative. I've learned so much!",
            name: 'Katie Wilson',
            rating: 4,
        },
        {
            image: person7,
            quote: "I found the perfect vet for my pet through Purrs N' Paws. Highly recommend!",
            name: 'Mike Davis',
            rating: 5,
        },
        {
            image: person8,
            quote: "Connecting with other pet owners has been a wonderful experience!",
            name: 'Laura White',
            rating: 5,
        }, 
    ];
    const chunkArray = (arr, size) => {
        const result = [];
        for (let i = 0; i < arr.length; i+= size) {
            result.push(arr.slice(i, i + size));
        }
        return result;
    }
    const groupedTestimonials = chunkArray(testimonials, 3);
    return (
        <S.TestimonialContainer>
            <S.Headline variant = 'h4' component = 'h2' align = 'center' gutterBottom>
                Awesome Paw-Some Testimonials
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
                { groupedTestimonials.map((group, index) => (
                    <Grid container spacing = { 2 } key = { index }>
                        { group.map((testimonial, idx) => (
                            <Grid item xs = { 12 } md = { 4 } key = { idx }>
                                <S.TestimonialCard>
                                    <S.TestimonialImage src = { testimonial.image } alt = { testimonial.name } />
                                    <S.TestimonialQuote variant = 'body1'>{ testimonial.quote }</S.TestimonialQuote>
                                    <Rating value = { testimonial.rating } readOnly />
                                    <S.TestimonialName variant = 'body2'>{ testimonial.name }</S.TestimonialName>
                                </S.TestimonialCard>
                            </Grid>
                        )) }
                    </Grid>
                )) }
            </Carousel>
        </S.TestimonialContainer>
    );
};

export default Testimonial;