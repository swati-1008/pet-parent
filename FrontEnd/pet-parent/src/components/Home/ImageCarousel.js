import React from 'react';
import Carousel from 'react-material-ui-carousel';
import * as S from './styles';
import image1 from '../../assets/images/Home/HomeCarousel/carousel1.png';
import image2 from '../../assets/images/Home/HomeCarousel/carousel2.png';
import image3 from '../../assets/images/Home/HomeCarousel/carousel3.png';
import image4 from '../../assets/images/Home/HomeCarousel/carousel4.png';
import image5 from '../../assets/images/Home/HomeCarousel/carousel5.png';

const ImageCarousel = () => {
    const ImageList = [image1, image2, image3, image4, image5];

    return (
        <S.CarouselBox>
            <Carousel
                autoPlay = { true }
                animation = 'slide'
                indicators = { true }
                navButtonsAlwaysVisible = { true }
                cycleNavigation = { true }
                interval = { 2000 }
                timeout = { 1000 }
                indicatorContainerProps = {{
                    style: {
                        marginTop: '-70px', 
                    }
                }}
            >
                { ImageList.map((image, index) => (
                    <S.CarouselImage src = { image } alt = { `Carousel ${index + 1}` } />
                )) }
            </Carousel>
        </S.CarouselBox>
    );
};

export default ImageCarousel;