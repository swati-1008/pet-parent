import * as S from './styles';
import CreatePost from "./CreatePost/CreatePost";
import LeftSidePanel from "./LeftSidePanel/LeftPanel";
import RightPanel from "./RightSidePanel/RightPanel";
import Posts from './Posts/Posts';

export function SocialNetwork () {
    return (
        <div style={{ display: 'flex' }}>
            <S.LeftPanel>
                <LeftSidePanel />
            </S.LeftPanel>
            <S.Body>
                <CreatePost />
                <Posts />
            </S.Body>
            <S.RightPanel>
                <RightPanel />
            </S.RightPanel>
        </div>
    )
}