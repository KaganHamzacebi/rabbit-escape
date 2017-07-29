module ViewToolbar exposing (viewToolbar)

import Html exposing
    ( Html
    , button
    , div
    , img
    , pre
    , programWithFlags
    , table
    , text
    , tr
    , td
    )
import Html.Attributes exposing (class, height, id, src, style, width)


import Msg exposing (Msg)
import Rabbit exposing (Direction(..))
import ToolbarDims exposing (ToolbarDims)
import ToolbarOrientation exposing (ToolbarOrientation(..))
import Units exposing (..)
import World exposing (BlockMaterial(..), BlockShape(..))


type ButtonDef =
      SaveButton String
    | BlockButton BlockMaterial BlockShape String
    | RabbitButton Direction String


margin : Pixels
margin =
    Pixels 2


buttonsList : List ButtonDef
buttonsList =
    [ SaveButton "save.svg"
    , BlockButton Earth Flat "land_block_1.png"
    , RabbitButton Left "rabbit_stand_left.svg"
    ]


styles : ToolbarDims -> List (Html.Attribute Msg)
styles tbdims =
    let
        th = tbdims.thickness |> px
        len = tbdims.thickness .*. (List.length buttonsList) |> px
        -- or max height of window
    in
        case tbdims.orientation of
            LeftToolbar ->
                [
                    style
                        [ ("width", th)
                        , ("height", len)
                        , ("overflow-x", "hidden")
                        , ("overflow-y", "auto")
                        ]
                ]
            TopToolbar ->
                [
                    style
                        [ ("width", len)
                        , ("height", th)
                        , ("overflow-x" ,"auto")
                        , ("overflow-y", "hidden")
                        , ("white-space", "nowrap")
                        ]
                ]


viewButton : ToolbarDims -> ButtonDef -> Html Msg
viewButton tbdims def =
    let
        imgfile =
            case def of
                SaveButton s -> s
                BlockButton _ _ s -> s
                RabbitButton _ s -> s
        marg = margin |> px
        size = (tbdims.thickness .-. (margin .*. 2)) |> px
        img_size = ((tbdims.thickness .**. 0.8) .-. (margin .*. 2)) |> px
    in
        button
            [ class "button"
            , style
                [ ("width", size)
                , ("height", size)
                , ("margin", marg)
                ]
            ]
            [ img
                [ class "buttonimg"
                , src ("game-images/" ++ imgfile)
                , style
                    [ ("width", img_size)
                    , ("height", img_size)
                    ]
                ]
                []
            ]

buttons : ToolbarDims -> List (Html Msg)
buttons tbdims =
    List.map (viewButton tbdims) buttonsList


viewToolbar : {x| toolbar : ToolbarDims } -> Html Msg
viewToolbar dims =
    div
        ([ id "toolbar" ] ++ styles dims.toolbar)
        (buttons dims.toolbar)