import React from "react";
import { Box, Typography } from "@mui/material";
import * as S from './styles';
import pawMartImage from '../../assets/images/Home/HomeFeatures/featureImage1.png';
import furTalksImage from '../../assets/images/Home/HomeFeatures/featureImage2.png';
import pawDocImage from '../../assets/images/Home/HomeFeatures/featureImage3.png';
import furryFeedsImage from '../../assets/images/Home/HomeFeatures/featureImage4.png';
import tailTalesImage from '../../assets/images/Home/HomeFeatures/featureImage5.jpeg';
import furFestImage from '../../assets/images/Home/HomeFeatures/featureImage6.png';

const featureData = [
    {
        title: 'Paw Mart', 
        content: 'Discover a wide variety of products tailored for your pets. From nutritious food to exciting toys, find everything you need in one place. Our carefully curated selection ensures your pets get the best of everything. Enjoy exclusive deals and convenient shopping all in one mart.', 
        image: pawMartImage, 
        reverse: false, 
    }, 
    {
        title: 'Fur Talks', 
        content: 'Join our vibrant community of pet owners in our interactive chat rooms. Share your experiences, get advice, and connect with fellow pet lovers. Whether you\'re a new pet parent or a seasoned one, you\'ll find valuable insights and heartwarming stories. Our chat rooms are safe and friendly, designed to foster meaningful connections.', 
        image: furTalksImage, 
        reverse: true, 
    }, 
    {
        title: 'PawDoc', 
        content: 'Consult with professional vets from the comfort of your home. Our vets are available to provide expert advice on any health concerns your pets might have. From routine check-ups to emergency consultations, we\'re here to help. Ensure your pet\'s health with easy and accessible veterinary services.', 
        image: pawDocImage, 
        reverse: false, 
    }, 
    {
        title: 'Furry Feeds', 
        content: 'Showcase your pet’s adorable moments on our dedicated social media platform. Connect with other pet enthusiasts and share the joy of being a pet parent. Follow, like, and comment on posts, and build a network of pet-loving friends. Celebrate your pet\'s milestones and everyday fun in a community that loves pets as much as you do.', 
        image: furryFeedsImage, 
        reverse: true, 
    }, 
    {
        title: 'Tail Tales', 
        content: 'Stay informed with the latest articles on pet care and health. Our blog covers tips, advice, and stories from the pet world. Learn about new products, training techniques, and wellness advice from experts. Whether you\'re looking for fun activities or serious health advice, our blog has something for every pet parent.', 
        image: tailTalesImage, 
        reverse: false, 
    }, 
    {
        title: 'FurFest', 
        content: 'Stay updated with the latest pet events happening around you. Join gatherings, competitions, and more to celebrate your pets. Our events calendar includes everything from local meet-ups to national pet shows. Don\'t miss out on opportunities to connect with other pet owners and have fun with your furry friends.', 
        image: furFestImage, 
        reverse: true, 
    }, 
];

const Feature = () => {
    return (
        <S.FeatureSection>
            <S.Headline variant = 'h4' component = 'h2' align = 'center' gutterBottom>
                Our Tail-Wagging Features
            </S.Headline>
            { featureData.map((feature, index) => (
                <S.FeatureContainer 
                    key = { index }
                    sx = {{ flexDirection: feature.reverse ? 'row-reverse' : 'row' }}
                >
                    <S.FeatureImageContainer>
                        <S.FeatureImage src = { feature.image } alt = { feature.title } />
                    </S.FeatureImageContainer>
                    <Box sx = {{ width: '10%' }}></Box>
                    <S.FeatureContentContainer>
                        <Typography variant = 'h4' gutterBottom style = {{ fontFamily: 'Satisfy, sans-serif', fontWeight: 'bold' }}>{ feature.title }</Typography>
                        <S.FeatureText>{ feature.content }</S.FeatureText>
                    </S.FeatureContentContainer>
                </S.FeatureContainer>
            )) }
        </S.FeatureSection>
    )
}

export default Feature;