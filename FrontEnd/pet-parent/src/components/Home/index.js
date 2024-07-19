import React from "react";
import * as S from './styles';
import logo from '../../assets/images/Home/logo.png';
import SearchBar from "./SearchBar";
import UserProfileMenu from "./UserProfileMenu";
import ImageCarousel from "./ImageCarousel";
import Feature from "./Feature";
import Testimonial from "./Testimonial";
import Blogs from "./Blogs";
import AboutUs from "./AboutUs";

const Home = () => {
    return (
        <>
            <S.StyledHeader>
                <S.StyledLogo src = { logo } alt = "Purrs N' Paws Logo"></S.StyledLogo>
                <S.StyledNavigation>
                    <S.NavLink href = '/retail-stores'>Pawmart</S.NavLink>
                    <S.NavLink href = '/chat-rooms'>Fur Talks</S.NavLink>
                    <S.NavLink href = '/vet-consultations'>PawDoc</S.NavLink>
                    <S.NavLink href = '/furry-feeds'>Furry Feeds</S.NavLink>
                    <S.NavLink href = '/blogs'>Tail Tales</S.NavLink>
                    <S.NavLink href = '/events'>FurFest</S.NavLink>
                </S.StyledNavigation>
                <SearchBar />
                <UserProfileMenu />
            </S.StyledHeader>
            <ImageCarousel />
            <Feature />
            <Testimonial />
            <Blogs />
            <AboutUs />
        </>
    )
}

export default Home;